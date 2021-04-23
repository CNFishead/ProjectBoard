package com.cooksys.server.services.implementations;

import com.cooksys.server.entities.*;
import com.cooksys.server.exceptions.BadRequestException;
import com.cooksys.server.exceptions.NotFoundException;
import com.cooksys.server.mappers.*;
import com.cooksys.server.models.CompanyDto;
import com.cooksys.server.models.ProjectDto;
import com.cooksys.server.models.TeamDto;
import com.cooksys.server.models.UserDto;
import com.cooksys.server.repositories.CompanyRepository;
import com.cooksys.server.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final UserMapper userMapper;
    private final TeamMapper teamMapper;
    private final ProjectMapper projectMapper;

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companyMapper.entitiesToDtos(companies);
    }

    @Override
    public CompanyDto getCompanyById(Long companyId) {
        Company company = findCompanyById(companyId);
        return companyMapper.entityToDto(company);
    }

    private Company findCompanyById(Long companyId) {
        for(Company company : companyRepository.findAll()) {
            if(company.getId().equals(companyId)) {
                return company;
            }
        }
        throw new NotFoundException("Requested company does not exist.");
    }

    @Override
    public List<UserDto> getUsersInCompany(Long companyId) {
        Company company = findCompanyById(companyId);
        List<User> employees = company.getEmployees();
        if (employees.size() == 0) {
            throw new NotFoundException("There are no users in this company");
        }
        return userMapper.entitiesToDtos(employees);
    }

    @Override
    public List<TeamDto> getTeamsInCompany(Long companyId) {
        Company company = findCompanyById(companyId);
        List<Team> teams = company.getTeams();
        if (teams.size() == 0) {
            throw new NotFoundException("There are no teams in this company");
        }
        return teamMapper.entitiesToDtos(teams);
    }

    @Override
    public List<ProjectDto> getProjectsInCompany(Long companyId) {
        Company company = findCompanyById(companyId);
        List<Project> projects = new ArrayList<Project>();
        List<Team> teams = company.getTeams();
        for (Team t : teams) {
            List<Project> team_proj = t.getProjects();
            for (Project p : team_proj) {
                projects.add(p);
            }
        }

        if (projects.size() == 0){
            throw new NotFoundException("There are no projects");
        }
        
        return projectMapper.entitiesToDtos(projects);
    }

    @Override
    public CompanyDto updateCompany(Long companyId, CompanyDto company) {
        Company company_to_change = findCompanyById(companyId);

        company_to_change.setName(company.getName());

        company_to_change.setDescription(company.getDescription());

        companyRepository.saveAndFlush(company_to_change);

        return companyMapper.entityToDto(company_to_change);
    }

	@Override
	public List<UserDto> getAdminsInCompany(Long companyId) {
		if (companyId == null) {
			throw new BadRequestException("you must provide a company ID");
		}
		
		Optional<Company> optionalCompany = companyRepository.findById(companyId);
		if (optionalCompany.isEmpty()) {
			throw new NotFoundException("the company with ID " + companyId + " does not exist");
		}
		
		List<User> activeAdmins = new ArrayList<>();
        for (User u : optionalCompany.get().getEmployees()) {
        	if (u.isActive() && u.getRole().getId() == 1) {
        		activeAdmins.add(u);
        	}
        }
        
        return userMapper.entitiesToDtos(activeAdmins);
	}


    
}
