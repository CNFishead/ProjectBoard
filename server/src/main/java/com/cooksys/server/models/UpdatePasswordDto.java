package com.cooksys.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdatePasswordDto {

    private String email;

    private String password;

    private String newPassword;

}
