package com.cooksys.server.controllers;

import com.cooksys.server.models.CreateTeamDto;
import com.cooksys.server.models.ProjectDto;
import com.cooksys.server.models.TeamDto;
import com.cooksys.server.models.UserDto;
import com.cooksys.server.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDto createTeam(@RequestBody CreateTeamDto createTeamDto) {
        return teamService.createTeam(createTeamDto);
    }

    @GetMapping("/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    public TeamDto getTeam(@PathVariable("teamId") Long teamId) {
        return teamService.getTeam(teamId);
    }

    @PatchMapping("/{teamId}/addUser/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public TeamDto addUser(@PathVariable("teamId") Long teamId, @PathVariable("userId") Long userId) {
        return teamService.addUser(teamId, userId);
    }

    @PatchMapping("/{teamId}/addProject/@{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public TeamDto addProject(@PathVariable("teamId") Long teamId, @PathVariable("projectId") Long projectId) {
        return teamService.addProject(teamId, projectId);
    }

    @GetMapping("/{teamId}/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers(@PathVariable("teamId") Long teamId) {
        return teamService.getUsers(teamId);
    }

    @GetMapping("/{teamId}/projects")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDto> getProjects(@PathVariable("teamId") Long teamId) {
        return teamService.getProjects(teamId);
    }

}
