package com.cooksys.server.models;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {

    private Long id;

    private String email;

    private ProfileDto profile;

    private Long companyId;

    private Long teamId;

    private Timestamp created;

    private Timestamp updated;

    // FIXME Role shouldn't be returned, it should be a boolean called admin.
    // private boolean admin;

    private RoleDto role;

}
