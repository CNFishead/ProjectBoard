package com.cooksys.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateProjectDto {

    private String title;

    private String description;

    private Long teamId;

}
