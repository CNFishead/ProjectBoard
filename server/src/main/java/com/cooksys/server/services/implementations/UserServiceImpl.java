package com.cooksys.server.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cooksys.server.entities.Company;
import com.cooksys.server.entities.Credential;
import com.cooksys.server.entities.Profile;
import com.cooksys.server.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import com.cooksys.server.entities.Role;
import com.cooksys.server.entities.Team;
import com.cooksys.server.entities.User;
import com.cooksys.server.exceptions.BadRequestException;
import com.cooksys.server.exceptions.NotFoundException;
import com.cooksys.server.mappers.CredentialMapper;
import com.cooksys.server.mappers.ProfileMapper;
import com.cooksys.server.mappers.RoleMapper;
import com.cooksys.server.mappers.TeamMapper;
import com.cooksys.server.mappers.UserMapper;
import com.cooksys.server.models.CreateUserDto;
import com.cooksys.server.models.RoleDto;
import com.cooksys.server.models.TeamDto;
import com.cooksys.server.models.UserDto;
import com.cooksys.server.repositories.CompanyRepository;
import com.cooksys.server.repositories.RoleRepository;
import com.cooksys.server.repositories.TeamRepository;
import com.cooksys.server.repositories.UserRepository;
import com.cooksys.server.services.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final CredentialMapper credentialMapper;
	private final ProfileMapper profileMapper;
	private final CompanyRepository companyRepository;
	private final TeamRepository teamRepository;
	private final RoleRepository roleRepository;
	private final RoleMapper roleMapper;
	private final TeamMapper teamMapper;

	@Override
	public List<UserDto> getAllUsers() {
		// Returns every active user in the repository. Mostly for testing Purposes.
		List<UserDto> activeUsers = new ArrayList<>();
		for (User u : userRepository.findAll()) {
			if (u.isActive()) {
				activeUsers.add(userMapper.entityToDto(u));
			}
		}
		return activeUsers;
	}

	// Endpoint tested.
	@Override
	public UserDto createUser(CreateUserDto createUserDto) {
		checkCreateUserDtoForNull(createUserDto);
		checkEmailAvailability(createUserDto.getCredentials().getEmail());
		User createdUser = userMapper.dtoToEntity(createUserDto);
		Company databaseCompany = getCompanyById(createUserDto.getCompanyId());
		createdUser.setUserCompany(databaseCompany);
		createdUser.setActive(true);
		Optional<Role> role = roleRepository.findById((long) 2);
		if(role.isPresent()) {
			createdUser.setRole(role.get());
		}
		createdUser = userRepository.saveAndFlush(createdUser);
		return userMapper.entityToDto(createdUser);
	}

	@Override
	public UserDto getUser(Long id) {
		if (id == null) {
			throw new BadRequestException("ID cannot be empty or null was provided: " + id);
		}
		Optional<User> optionalUser = userRepository.findById(id);
		// User Validation to check to see if the user exists.
		checkExistsNotDeleted(optionalUser.get().getId(), optionalUser);

		// Return user entity DTO
		return userMapper.entityToDto(optionalUser.get());
	}

	@Override
	public UserDto updateUserProfile(Long id, CreateUserDto createUserDto) {
		if (id == null) {
			throw new NotFoundException("Must Provide a user ID");
		}
		// check user exists and is not deleted
		Optional<User> optUser = userRepository.findById(id);
		checkExistsNotDeleted(id, optUser);

		// update user's profile, flush, and return UserDto
		optUser.get().setProfile(profileMapper.dtoToEmbeddable(createUserDto.getProfile()));
		userRepository.flush();
		return userMapper.entityToDto(optUser.get());
	}

	@Override
	public UserDto deleteUser(Long id) {
		if (id == null) {
			throw new NotFoundException("Must Provide a user ID");
		}
		// Check user exists and is not deleted
		Optional<User> optUser = userRepository.findById(id);
		if (optUser.isEmpty()) {
			throw new NotFoundException("The user with id: " + id + " does not exist");
		} else if (!optUser.get().isActive()) {
			throw new BadRequestException("User has already been deleted");
		}

		optUser.get().setActive(false);
		userRepository.flush();
		return userMapper.entityToDto(optUser.get());
	}

	@Override
	public UserDto addRole(Long id, RoleDto role) {
		if (id == null) {
			throw new NotFoundException("Must Provide a user ID");
		}
		Optional<User> optUser = userRepository.findById(id);
		checkExistsNotDeleted(id, optUser);
		if (role.getId() != 1 && role.getId() != 2) {
			throw new BadRequestException("User can only be admin, or user");
		} else {
			Optional<Role> newRole = roleRepository.findById(role.getId()); //saveAndFlush(roleMapper.DtoToEntity(role));
			
			optUser.get().setRole(newRole.get());
			System.out.println(role.getName());
			return userMapper.entityToDto(userRepository.saveAndFlush(optUser.get()));
		}

	}

	@Override
	public UserDto addTeam(Long id, TeamDto team) {
		if (id == null) {
			throw new NotFoundException("Must Provide a user ID");
		}
		Optional<User> optUser = userRepository.findById(id);
		checkExistsNotDeleted(id, optUser);
		if (teamRepository.findById(team.getId()).isEmpty()) {
			throw new NotFoundException("Team Does not Exist");
		} else {
			optUser.get().setUserTeam(teamRepository.saveAndFlush(teamMapper.DtoToEntity(team)));
			return userMapper.entityToDto(userRepository.saveAndFlush(optUser.get()));
		}

	}

	// Helper methods

	// Takes an Id and and a User Object. if Optional User is empty the user doesnt
	// exist
	public void checkExistsNotDeleted(Long id, Optional<User> optionalUser) {
		if (optionalUser.isEmpty()) {
			throw new NotFoundException("the user with id: " + id + "Does not exist");
			// If User is found but is NOT active, means the user has been "deleted"
		} else if (!optionalUser.get().isActive()) {
			throw new NotFoundException(
					"The user " + optionalUser.get().getProfile().getFirstName() + " has been deleted");
		}
	}

	// Checks for all possible null values when creating a user.
	private void checkCreateUserDtoForNull(CreateUserDto createUserDto) {

		if(createUserDto == null) {
			throw new BadRequestException("Request body not provided.");
		}

		if(createUserDto.getProfile() == null) {
			throw new BadRequestException("Profile information must be provided.");
		}

		if(createUserDto.getProfile().getFirstName() == null) {
			throw new BadRequestException("First name must be provided.");
		}

		if(createUserDto.getProfile().getLastName() == null) {
			throw new BadRequestException("Last name must be provided.");
		}

		if(createUserDto.getProfile().getPhone() == null) {
			throw new BadRequestException("Phone number must be provided.");
		}

		if(createUserDto.getCredentials() == null) {
			throw new BadRequestException("Credentials must be provided.");
		}

		if(createUserDto.getCredentials().getEmail() == null) {
			throw new BadRequestException("Email must be provided.");
		}

		if(createUserDto.getCredentials().getPassword() == null) {
			throw new BadRequestException("Password must be provided.");
		}

		if(createUserDto.getCompanyId() == null) {
			throw new BadRequestException("Company ID must be provided.");
		}
	}

	// Returns the company that corresponds to the provided ID, otherwise throws not found exception.
	private Company getCompanyById(Long companyId) {
		for(Company company : companyRepository.findAll()) {
			if(company.getId().equals(companyId)) {
				return company;
			}
		}
		throw new NotFoundException("Company with that ID does not exist.");
	}

	// Checks if email is available in the database, otherwise throws unauthorized exception.
	private void checkEmailAvailability(String email) {
		for(User user : userRepository.findAll()) {
			if(user.getCredentials().getEmail().equals(email)) {
				throw new UnauthorizedException("User with this email already exists.");
			}
		}
	}

}
