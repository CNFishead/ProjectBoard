package com.cooksys.server.services;

import com.cooksys.server.models.*;

import java.util.List;

public interface CompanyService {

    List<CompanyDto> getAllCompanies();

    CompanyDto getCompanyById(Long companyId);

    List<UserDto> getUsersInCompany(Long companyId);

    List<TeamDto> getTeamsInCompany(Long companyId);

    List<ProjectDto> getProjectsInCompany(Long companyId);

    CompanyDto updateCompany(Long companyId, CompanyDto company);

	List<UserDto> getAdminsInCompany(Long companyId);
}
