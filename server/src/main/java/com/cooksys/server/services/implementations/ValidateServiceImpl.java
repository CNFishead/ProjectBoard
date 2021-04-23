package com.cooksys.server.services.implementations;

import com.cooksys.server.entities.User;
import com.cooksys.server.exceptions.BadRequestException;
import com.cooksys.server.exceptions.UnauthorizedException;
import com.cooksys.server.mappers.UserMapper;
import com.cooksys.server.models.CredentialDto;
import com.cooksys.server.models.UpdatePasswordDto;
import com.cooksys.server.models.UserDto;
import com.cooksys.server.repositories.UserRepository;
import com.cooksys.server.services.ValidateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidateServiceImpl implements ValidateService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto login(CredentialDto credentialDto) {
        User databaseUser = getUserByEmail(credentialDto.getEmail());
        String incomingPassword = credentialDto.getPassword();
        if(!databaseUser.getCredentials().getPassword().equals(incomingPassword)) {
            throw new UnauthorizedException("Error logging in, please check credentials.");
        }
        return userMapper.entityToDto(databaseUser);
    }

    @Override
    public UserDto changePassword(UpdatePasswordDto updatePasswordDto) {
        verifyUpdatePasswordNotNull(updatePasswordDto);
        User databaseUser = getUserByEmail(updatePasswordDto.getEmail());
        verifyCredentials(updatePasswordDto, databaseUser);
        databaseUser.getCredentials().setPassword(updatePasswordDto.getNewPassword());
        databaseUser = userRepository.saveAndFlush(databaseUser);
        return userMapper.entityToDto(databaseUser);
    }

    private User getUserByEmail(String email) {
        for(User user : userRepository.findAll()) {
            if(user.getCredentials().getEmail().equals(email)) {
                return user;
            }
        }
        throw new UnauthorizedException("Error logging in, please check credentials.");
    }

    private void verifyUpdatePasswordNotNull(UpdatePasswordDto updatePasswordDto) {
        if(updatePasswordDto == null) {
            throw new BadRequestException("Request body must be provided.");
        }
        if(updatePasswordDto.getEmail() == null) {
            throw new BadRequestException("Email must be provided");
        }
        if(updatePasswordDto.getPassword() == null) {
            throw new BadRequestException("Old password must be provided.");
        }
        if(updatePasswordDto.getNewPassword() ==  null) {
            throw new BadRequestException("New password must be provided.");
        }
    }

    private void verifyCredentials(UpdatePasswordDto updatePasswordDto, User databaseUser) {
        if(!updatePasswordDto.getPassword().equals(databaseUser.getCredentials().getPassword())) {
            throw new UnauthorizedException("Error updating password, please try again.");
        }
    }

}
