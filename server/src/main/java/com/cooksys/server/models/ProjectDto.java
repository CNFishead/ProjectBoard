package com.cooksys.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProjectDto {

    private Long id;

    private String title;

    private String description;

    private Long teamId;

    private boolean completed;

}
