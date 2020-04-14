package com.example.demo.controller;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.gateway.ApiGateway;
import com.example.demo.models.UserApiResponse;
import com.example.demo.service.UserService;


@RestController
public class UserController {

	
	    @Autowired
	    private UserService userService;

	    @GetMapping("/user/{id}")
	    public UserApiResponse getRepo(@PathVariable long id) throws IOException {
	        return userService.getRepositories(id);
	    }

}
