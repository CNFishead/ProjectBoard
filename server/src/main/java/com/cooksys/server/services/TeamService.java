package com.cooksys.server.services;

import com.cooksys.server.models.CreateTeamDto;
import com.cooksys.server.models.ProjectDto;
import com.cooksys.server.models.TeamDto;
import com.cooksys.server.models.UserDto;

import java.util.List;

public interface TeamService {

    TeamDto createTeam(CreateTeamDto createTeamDto);

    TeamDto getTeam(Long teamId);

    TeamDto addUser(Long teamId, Long userId);

    TeamDto addProject(Long teamId, Long projectId);

    List<UserDto> getUsers(Long teamId);

    List<ProjectDto> getProjects(Long teamId);

}
