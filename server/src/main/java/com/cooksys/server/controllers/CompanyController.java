package com.cooksys.server.controllers;

import com.cooksys.server.services.UserService;

import com.cooksys.server.models.*;
import com.cooksys.server.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/company")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyDto> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto getCompanyById(@PathVariable Long companyId) {
        return companyService.getCompanyById(companyId);
    }

    @GetMapping("/{companyId}/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsersInCompany(@PathVariable Long companyId) {
        return companyService.getUsersInCompany(companyId);
    }
    
    @GetMapping("/{companyId}/admins")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAdminsInCompany(@PathVariable Long companyId) {
    	return companyService.getAdminsInCompany(companyId);
    }

    @GetMapping("/{companyId}/teams")
    @ResponseStatus(HttpStatus.OK)
    public List<TeamDto> getTeamsInCompany(@PathVariable Long companyId) {
        return companyService.getTeamsInCompany(companyId);
    }

    @GetMapping("/{companyId}/projects")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDto> getProjectsInCompany(@PathVariable Long companyId) {
        return companyService.getProjectsInCompany(companyId);
    }

    @PatchMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto updateCompany(@PathVariable Long companyId, @RequestBody CompanyDto company) {
        return companyService.updateCompany(companyId, company);
    }
}
