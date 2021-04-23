package com.cooksys.server.services.implementations;

import com.cooksys.server.entities.Project;
import com.cooksys.server.entities.Team;
import com.cooksys.server.exceptions.BadRequestException;
import com.cooksys.server.exceptions.NotFoundException;
import com.cooksys.server.mappers.ProjectMapper;
import com.cooksys.server.models.CreateProjectDto;
import com.cooksys.server.models.ProjectDto;
import com.cooksys.server.models.UpdateProjectDto;
import com.cooksys.server.repositories.ProjectRepository;
import com.cooksys.server.repositories.TeamRepository;
import com.cooksys.server.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final TeamRepository teamRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectDto createProject(CreateProjectDto createProjectDto) {

        checkCreateProjectDtoForNull(createProjectDto);

        Team databaseTeam = getTeamById(createProjectDto.getTeamId());

        Project createdProject = new Project();

        createdProject.setTitle(createProjectDto.getTitle());
        createdProject.setDescription(createProjectDto.getDescription());
        createdProject.setProjectTeam(databaseTeam);

        createdProject = projectRepository.saveAndFlush(createdProject);

        return projectMapper.entityToDto(createdProject);

    }

    @Override
    public ProjectDto getProject(Long projectId) {

        Project databaseProject = getProjectById(projectId);

        checkProjectDeletionStatus(databaseProject);

        return projectMapper.entityToDto(databaseProject);

    }

    @Override
    public ProjectDto updateProject(Long projectId, UpdateProjectDto updateProjectDto) {

        checkUpdateProjectDtoForNull(updateProjectDto);

        Project databaseProject = getProjectById(projectId);

        databaseProject.setTitle(updateProjectDto.getTitle());
        databaseProject.setDescription(updateProjectDto.getDescription());

        projectRepository.save(databaseProject);

        return projectMapper.entityToDto(databaseProject);

    }

    @Override
    public ProjectDto deleteProject(Long projectId) {
        Project databaseProject = getProjectById(projectId);
        databaseProject.setDeleted(true);
        projectRepository.saveAndFlush(databaseProject);
        return projectMapper.entityToDto(databaseProject);
    }

    @Override
    public ProjectDto completeProject(Long projectId) {
        Project databaseProject = getProjectById(projectId);
        databaseProject.setCompleted(true);
        projectRepository.saveAndFlush(databaseProject);
        return projectMapper.entityToDto(databaseProject);
    }

    // Helper methods

    // Checks createProjectDto for null values, throws errors accordingly.
    private void checkCreateProjectDtoForNull(CreateProjectDto createProjectDto) {
        if(createProjectDto == null) {
            throw new BadRequestException("Request body must be provided.");
        }

        if(createProjectDto.getTitle() == null) {
            throw new BadRequestException("Project title must be provided.");
        }

        if(createProjectDto.getDescription() == null) {
            throw new BadRequestException("Project description must be provided.");
        }

        if(createProjectDto.getTeamId() == null) {
            throw new BadRequestException("Team ID for the project must be provided");
        }
    }

    // Returns the team that corresponds to the passed ID. Throws error otherwise.
    private Team getTeamById(Long teamId) {
        for(Team team : teamRepository.findAll()) {
            if(team.getId().equals(teamId)) {
                return team;
            }
        }
        throw new NotFoundException("Specified team does not exist.");
    }

    // Returns the project that corresponds to the id passed, or error if not found.
    private Project getProjectById(Long projectId) {
        for(Project project : projectRepository.findAll()) {
            if(project.getId().equals(projectId)) {
                return project;
            }
        }
        throw new NotFoundException("Requested project does not exist.");
    }

    // Throws error if passed project is deleted.
    private void checkProjectDeletionStatus(Project databaseProject) {
        if(databaseProject.isDeleted()) {
            throw new NotFoundException("Requested project does not exist.");
        }
    }

    // Checks updateProjectDto for null values and throws errors accordingly.
    private void checkUpdateProjectDtoForNull(UpdateProjectDto updateProjectDto) {

        if(updateProjectDto == null) {
            throw new BadRequestException("Request body must be provided.");
        }

        if(updateProjectDto.getTitle() == null) {
            throw new BadRequestException("Title must be provided.");
        }

        if(updateProjectDto.getDescription() == null) {
            throw new BadRequestException("Description must be provided.");
        }

    }
}
