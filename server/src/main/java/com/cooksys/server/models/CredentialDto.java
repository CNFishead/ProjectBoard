package com.cooksys.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CredentialDto {

    private String email;

    private String password;
    
}
