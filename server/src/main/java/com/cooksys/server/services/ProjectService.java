package com.cooksys.server.services;

import com.cooksys.server.models.CreateProjectDto;
import com.cooksys.server.models.ProjectDto;
import com.cooksys.server.models.UpdateProjectDto;

public interface ProjectService {

    ProjectDto createProject(CreateProjectDto createProjectDto);

    ProjectDto getProject(Long projectId);

    ProjectDto updateProject(Long projectId, UpdateProjectDto updateProjectDto);

    ProjectDto deleteProject(Long projectId);

    ProjectDto completeProject(Long projectId);

}
