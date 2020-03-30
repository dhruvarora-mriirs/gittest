package com.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.exceptions.RecordNotFoundException;
import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;

	public List<User> getAllUser() {
		return repo.findAll();
	}


	public Optional<User> getUser(@Positive long id) {

		return repo.findById(id);

	}

	public User saveUser(User user) {
		
	
		return repo.save(user);

	}

	public void Delete(@Positive long id) {
		repo.deleteById(id);
	}

		
	public void updateUser(User requestuser, User dbUser) {
       
		User users = User.UserBuilder.createUser(requestuser, dbUser);
			repo.save(users);

	}

	
	public List<User> getAllUsers(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
 
        Page<User> pagedResult = repo.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<User>();
        }
	}
	
	public Optional<User> getByUserName(String userName) {
		
		return repo.findByUserName(userName);
	}
	
	public Optional<User> getByDob(String dob) 
	{
		Date dobs;
		try {
			dobs = new SimpleDateFormat().parse(dob);
		
		return repo.findByDob(dobs);
	}
	catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		throw new RecordNotFoundException("No user with this dob : " + dob);

	}
	
}
