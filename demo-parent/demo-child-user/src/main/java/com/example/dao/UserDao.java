package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.DAOUser;


@Repository
public interface UserDao extends JpaRepository<DAOUser, Long> {
	
	
	DAOUser findByUserName(String username);
	
}