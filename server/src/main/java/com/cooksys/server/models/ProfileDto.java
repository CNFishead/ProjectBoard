package com.cooksys.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProfileDto {
    
    private String firstName;

    private String lastName;

    private String phone;

}
