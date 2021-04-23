package com.cooksys.server.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cooksys.server.entities.Role;
import com.cooksys.server.entities.Team;
import com.cooksys.server.models.CreateUserDto;
import com.cooksys.server.models.RoleDto;
import com.cooksys.server.models.TeamDto;
import com.cooksys.server.models.UserDto;
import com.cooksys.server.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

	private final UserService userService;

	@GetMapping
	public List<UserDto> getAllUsers(){
		return userService.getAllUsers();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto createUser(@RequestBody CreateUserDto createUserDto) {
		return userService.createUser(createUserDto);
	}

	@GetMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public UserDto getUser(@PathVariable("userId") Long id) {
		return userService.getUser(id);
	}

	@PatchMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public UserDto updateUserProfile(@PathVariable("userId") Long id, @RequestBody CreateUserDto createUserDto) {
		return userService.updateUserProfile(id, createUserDto);
	}
	
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public UserDto deleteUser(@PathVariable("userId") Long id) {
		return userService.deleteUser(id);
	}
	
	//Should take a user id, and a request body that will be used to set a role.
	//This may change from a request body to a Long Variable later.
	@PatchMapping("/{userId}/role")
	@ResponseStatus(HttpStatus.OK)
	public UserDto addRole(@PathVariable("userId") Long id, @RequestBody RoleDto role) {
		return userService.addRole(id, role);
	}
	
	@PatchMapping("/{userId}/teams")
	@ResponseStatus(HttpStatus.OK)
	public UserDto addTeam(@PathVariable("userId") Long id, @RequestBody TeamDto team) {
		return userService.addTeam(id, team);
		
	}
}
