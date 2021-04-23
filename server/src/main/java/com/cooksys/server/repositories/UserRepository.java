package com.cooksys.server.repositories;

import com.cooksys.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByCredentialsEmail(String email);
    
}
