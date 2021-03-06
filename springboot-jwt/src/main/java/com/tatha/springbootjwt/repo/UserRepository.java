package com.tatha.springbootjwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tatha.springbootjwt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
 
	User findByUsername(String userName);
}
