package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;

import com.example.model.Users;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	public Optional<Users> getUser(@Positive long id) {

		return userDao.findById(id);
		

	}

	
	public Users saveUser(Users user) {

		return userDao.save(user);

	}

	

	
	public void Delete(@Positive long id) {
		userDao.deleteById(id);
	}



	public List<Users> getAllUsers(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
 
        Page<Users> pagedResult = userDao.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Users>();
        }
	}
	
}
