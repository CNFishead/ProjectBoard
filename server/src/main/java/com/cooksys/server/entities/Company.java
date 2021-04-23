package com.cooksys.server.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "userCompany")
    private List<User> employees = new ArrayList<>();

    @OneToMany(mappedBy = "teamCompany")
    private List<Team> teams = new ArrayList<>();

    // Custom constructor to create simple Company
    public Company(String name, String description) {
        this.name = name;
        this.description = description;
    }

}