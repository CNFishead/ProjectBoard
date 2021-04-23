package com.cooksys.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateUserDto {

    private CredentialDto credentials;
    
    private ProfileDto profile;

    private Long companyId;

}
