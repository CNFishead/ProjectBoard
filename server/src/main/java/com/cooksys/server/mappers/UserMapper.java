package com.cooksys.server.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import com.cooksys.server.entities.User;
import com.cooksys.server.models.CreateUserDto;
import com.cooksys.server.models.UserDto;

@Mapper(componentModel = "spring", uses = { ProfileMapper.class, CredentialMapper.class, CompanyMapper.class, TeamMapper.class })
public interface UserMapper {

    @Mapping(target = "userTeam", ignore = true)
    @Mapping(target = "userCompany", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "active", ignore = true)
    User dtoToEntity(CreateUserDto createUserDto);

    @Mapping(target = "userTeam", ignore = true)
    @Mapping(target = "userCompany", ignore = true)
    @Mapping(target = "credentials", ignore = true)
    @Mapping(target = "active", ignore = true)
    User dtoTo_get_Entity(UserDto userDto);

    @Mapping(target= "email", source="credentials.email")
    @Mapping(target = "companyId", source = "userCompany.id")
    @Mapping(target = "teamId", source = "userTeam.id")
    UserDto entityToDto(User user);

    List<UserDto> entitiesToDtos(List<User> users);
    
}
