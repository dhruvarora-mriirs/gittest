package com.example.repository;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long>{

  public Optional<User> findByUserName(String userName);

	public Optional<User> findByDob(Date dob);
}
