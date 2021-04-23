package com.cooksys.server.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.server.entities.Project;
import com.cooksys.server.entities.Team;
import com.cooksys.server.entities.User;
import com.cooksys.server.exceptions.BadRequestException;
import com.cooksys.server.exceptions.NotFoundException;
import com.cooksys.server.mappers.ProjectMapper;
import com.cooksys.server.mappers.TeamMapper;
import com.cooksys.server.mappers.UserMapper;
import com.cooksys.server.models.CreateTeamDto;
import com.cooksys.server.models.ProjectDto;
import com.cooksys.server.models.TeamDto;
import com.cooksys.server.models.UserDto;
import com.cooksys.server.repositories.CompanyRepository;
import com.cooksys.server.repositories.ProjectRepository;
import com.cooksys.server.repositories.TeamRepository;
import com.cooksys.server.repositories.UserRepository;
import com.cooksys.server.services.TeamService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {
	
	private final TeamRepository teamRepo;
	private final CompanyRepository companyRepo;
	private final TeamMapper teamMapper;
	private final UserRepository userRepo;
	private final ProjectRepository projectRepo;
	private final UserMapper userMapper;
	private final ProjectMapper projectMapper;
	
	// Helper methods
	public void checkTeamExistsNotDeleted(Long teamId, Optional<Team> optionalTeam) {
		if (optionalTeam.isEmpty()) {
			throw new NotFoundException("the team with ID " + teamId + " does not exist");
		} else if (optionalTeam.get().isDeleted()) {
			throw new NotFoundException("the team with ID " + teamId + " has been deleted");
		}
	}
	
	public void checkUserExistsNotDeleted(Long userId, Optional<User> optionalUser) {
		if (optionalUser.isEmpty()) {
			throw new NotFoundException("the user with ID " + userId + " does not exist");
		} else if (!optionalUser.get().isActive()) {
			throw new NotFoundException("the user with ID " + userId + " has been deleted");
		}
	}
	
	public void checkProjectExistsNotDeleted(Long projectId, Optional<Project> optionalProject) {
		if (optionalProject.isEmpty()) {
			throw new NotFoundException("the project with ID " + projectId + " does not exist");
		} else if (optionalProject.get().isDeleted()) {
			throw new NotFoundException("the project with ID " + projectId + " has been deleted");
		}
	}
	
	public void nullIdCheck(Long id, String idType) {
		if (id == null) {
			throw new BadRequestException("you must provide a " + idType + " ID");
		}
	}

    // POST /team endpoint. It should create a team and return it.
    @Override
    public TeamDto createTeam(CreateTeamDto createTeamDto) {
    	// check for and throw errors
        if (createTeamDto.getName() == null || createTeamDto.getName().isEmpty()) {
        	throw new BadRequestException("you must provide a team name");
        }
        if (createTeamDto.getText() == null ||createTeamDto.getText().isEmpty()) {
        	throw new BadRequestException("you must provide a team description");
        }
        if (createTeamDto.getCompanyId() == null) {
        	throw new BadRequestException("you must provide the company's ID");
        } else if (!companyRepo.existsById(createTeamDto.getCompanyId())) {
        	throw new NotFoundException("no company exists with the id " + createTeamDto.getCompanyId());
        }
        Optional<Team> optionalTeam = teamRepo.findByTeamName(createTeamDto.getName());
        if (optionalTeam.isPresent() && optionalTeam.get().getTeamCompany().getId().equals(createTeamDto.getCompanyId())) {
        	throw new BadRequestException("a team with the name " + createTeamDto.getName() + " already exists in this company");
        }
        // no errors, create new team and add it to company's team list
        Team teamToSave = new Team();
        teamToSave.setDeleted(false);
        teamToSave.setTeamName(createTeamDto.getName());
        teamToSave.setText(createTeamDto.getText());
        teamToSave.setTeamCompany(companyRepo.getOne(createTeamDto.getCompanyId()));
        companyRepo.getOne(createTeamDto.getCompanyId()).getTeams().add(teamToSave);
        companyRepo.flush();
        return teamMapper.entityToDto(teamRepo.saveAndFlush(teamToSave));
    }

    // GET /team/@{teamId} endpoint. It should get the team from the specified ID and return it.
    @Override
    public TeamDto getTeam(Long teamId) {
    	nullIdCheck(teamId, "team");
    	
    	Optional<Team> optionalTeam = teamRepo.findById(teamId);
    	checkTeamExistsNotDeleted(teamId, optionalTeam);
    	
    	return teamMapper.entityToDto(optionalTeam.get());
    }

    // PATCH /team/@{teamId}/addUser/@{userId} endpoint. It should add the user by user ID to the team by team ID and return the team.
    @Override
    public TeamDto addUser(Long teamId, Long userId) {
        nullIdCheck(teamId, "team");
        nullIdCheck(userId, "user");
        
        Optional<Team> optionalTeam = teamRepo.findById(teamId);
        checkTeamExistsNotDeleted(teamId, optionalTeam);
        
        Optional<User> optionalUser = userRepo.findById(userId);
        checkUserExistsNotDeleted(userId, optionalUser);
        
        if (optionalUser.get().getUserTeam() != null) {
        	if (optionalUser.get().getUserTeam().getId().equals(teamId)) {
        		throw new BadRequestException("the user is already a member of this team");
        	} else {
        		throw new BadRequestException("the user is already a member of a different team");
        	}
        }
        if (!optionalUser.get().getUserCompany().getId().equals(optionalTeam.get().getTeamCompany().getId())) {
        	throw new BadRequestException("the user and team provided do not belong to the same company");
        }
        
        optionalTeam.get().getMembers().add(optionalUser.get());
        optionalUser.get().setUserTeam(optionalTeam.get());
        
        teamRepo.flush();
        userRepo.flush();
        return teamMapper.entityToDto(optionalTeam.get());
    }

    // PATCH /team/@{teamId}/addProject/@{projectId} endpoint. It should add the project by project ID to the team by team ID and return the team.
    @Override
    public TeamDto addProject(Long teamId, Long projectId) {
        nullIdCheck(teamId, "team");
        nullIdCheck(projectId, "project");
        
        Optional<Team> optionalTeam = teamRepo.findById(teamId);
        checkTeamExistsNotDeleted(teamId, optionalTeam);
        
        Optional<Project> optionalProject = projectRepo.findById(projectId);
        checkProjectExistsNotDeleted(projectId, optionalProject);
        
        if (optionalProject.get().getProjectTeam() != null) {
        	if (optionalProject.get().getProjectTeam().getId().equals(teamId)) {
        		throw new BadRequestException("the project already belongs to this team");
        	} else {
        		throw new BadRequestException("the project already belongs to a different team");
        	}
        }
        
        optionalTeam.get().getProjects().add(optionalProject.get());
        optionalProject.get().setProjectTeam(optionalTeam.get());
        
        teamRepo.flush();
        projectRepo.flush();
        return teamMapper.entityToDto(optionalTeam.get());
    }

    // GET /team/@{teamId}/users endpoint. It should return a list of users that belong to the team specified by the team ID.
    @Override
    public List<UserDto> getUsers(Long teamId) {
        nullIdCheck(teamId, "team");
        
        Optional<Team> optionalTeam = teamRepo.findById(teamId);
        checkTeamExistsNotDeleted(teamId, optionalTeam);
        
        List<User> activeUsers = new ArrayList<>();
        for (User u : optionalTeam.get().getMembers()) {
        	if (u.isActive()) {
        		activeUsers.add(u);
        	}
        }
        
        return userMapper.entitiesToDtos(activeUsers);
    }

    // GET /team/@{teamId}/projects endpoint. It should return a list of projects that belong to the team specified by the team ID.
    @Override
    public List<ProjectDto> getProjects(Long teamId) {
        nullIdCheck(teamId, "team");
        
        Optional<Team> optionalTeam = teamRepo.findById(teamId);
        checkTeamExistsNotDeleted(teamId, optionalTeam);
        
        List<Project> existingProjects = new ArrayList<>();
        for (Project p : optionalTeam.get().getProjects()) {
        	if (!p.isDeleted()) {
        		existingProjects.add(p);
        	}
        }
        
        return projectMapper.entitiesToDtos(existingProjects);
    }

}