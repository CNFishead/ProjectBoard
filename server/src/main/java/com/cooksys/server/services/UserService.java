package com.cooksys.server.services;

import java.util.List;

import com.cooksys.server.entities.Role;
import com.cooksys.server.entities.Team;
import com.cooksys.server.models.CreateUserDto;
import com.cooksys.server.models.RoleDto;
import com.cooksys.server.models.TeamDto;
import com.cooksys.server.models.UserDto;

public interface UserService {

	UserDto getUser(Long id);

	List<UserDto> getAllUsers();

	UserDto createUser(CreateUserDto createUserDto);

	UserDto updateUserProfile(Long id, CreateUserDto createUserDto);

	UserDto deleteUser(Long id);

	UserDto addRole(Long id, RoleDto role);

	UserDto addTeam(Long id, TeamDto team);

}
