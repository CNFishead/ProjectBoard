package com.cooksys.server.mappers;

import com.cooksys.server.entities.Project;
import com.cooksys.server.models.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "teamId", source = "projectTeam.id")
    ProjectDto entityToDto(Project project);

    List<ProjectDto> entitiesToDtos(List<Project> projects);

}
