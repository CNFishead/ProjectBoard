package com.cooksys.server.entities;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn
	private Company userCompany;

	@ManyToOne
	@JoinColumn
	private Team userTeam;

	@OneToOne
	private Role role;

	@Embedded
	private Credential credentials;

	@Embedded
	private Profile profile;

	@CreationTimestamp
	private Timestamp created;

	@UpdateTimestamp
	private Timestamp updated;

	private boolean active;

}