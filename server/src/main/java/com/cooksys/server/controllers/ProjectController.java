package com.cooksys.server.controllers;

import com.cooksys.server.models.CreateProjectDto;
import com.cooksys.server.models.ProjectDto;
import com.cooksys.server.models.UpdateProjectDto;
import com.cooksys.server.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto createProject(@RequestBody CreateProjectDto createProjectDto) {
        return projectService.createProject(createProjectDto);
    }

    @GetMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto getProject(@PathVariable("projectId") Long projectId) {
        return projectService.getProject(projectId);
    }

    @PatchMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto updateProject(@PathVariable("projectId") Long projectId, @RequestBody UpdateProjectDto updateProjectDto) {
        return projectService.updateProject(projectId, updateProjectDto);
    }

    @DeleteMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto deleteProject(@PathVariable("projectId") Long projectId) {
        return projectService.deleteProject(projectId);
    }

    @PatchMapping("/{projectId}/complete")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto completeProject(@PathVariable("projectId") Long projectId) {
        return projectService.completeProject(projectId);
    }

}
