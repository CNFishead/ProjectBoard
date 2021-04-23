package com.cooksys.server.entities;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private Company teamCompany;

    private String teamName;

    private String text;

    private boolean deleted;

    @OneToMany(mappedBy = "userTeam")
    private List<User> members = new ArrayList<>();

    @OneToMany(mappedBy = "projectTeam")
    private List<Project> projects = new ArrayList<>();

}