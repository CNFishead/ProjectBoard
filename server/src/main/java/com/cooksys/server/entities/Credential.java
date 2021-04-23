package com.cooksys.server.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Credential {

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
}