package com.cooksys.server.repositories;

import com.cooksys.server.entities.Team;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{

    
	Optional<Team> findByTeamName(String teamName);
	
}
