package com.cooksys.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@Data
public class TeamDto {

    private Long id;

    private String teamName;

    private String text;

    private CompanyDto teamCompany;

    private List<ProjectDto> projects;

    private List<UserDto> members;

}
