package com.cooksys.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateTeamDto {

    private String name;

    private String text;

    private Long companyId;

}
