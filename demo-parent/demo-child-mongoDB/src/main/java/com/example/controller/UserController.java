package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.exceptions.RecordNotFoundException;
import com.example.model.User;
import com.example.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	
	

	


		@Autowired
		private UserService services;


		 @GetMapping
		    public ResponseEntity<List<User>> getAllUser(
		                        @RequestParam(defaultValue = "0") Integer pageNo, 
		                        @RequestParam(defaultValue = "10") Integer pageSize,
		                        @RequestParam(defaultValue = "id") String sortBy) 
		    
		    {
		        List<User> list = services.getAllUsers(pageNo, pageSize, sortBy);
		        if(list.isEmpty())
		        return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.NO_CONTENT); 
		        
		        return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
		    
		    
		    }
			
		
		

		@GetMapping("/{id}")
		public ResponseEntity<User> getUser(@PathVariable @Positive long id) {

			Optional<User> user = services.getUser(id);
			if (user.isPresent()) {
				return new ResponseEntity<>(services.getUser(id).get(), HttpStatus.OK);
			}

			throw new RecordNotFoundException("Invalid user id : " + id);

		}

		@PostMapping
		public ResponseEntity<User> addUser(@Valid @RequestBody User user)throws Exception {
	     
		    
			return new ResponseEntity<>(services.saveUser(user), HttpStatus.CREATED);
	      
		}

		@PutMapping("/{id}")
		public ResponseEntity<String> updateUser(@Valid @RequestBody User user) {
			long id = user.getId();
			Optional<User> user1 = services.getUser(id);
			if (user1.isPresent()) {
				services.saveUser(user);
				return ResponseEntity.status(HttpStatus.OK).body("user found");
			}
			throw new RecordNotFoundException("Invalid user id : " + id);

		}

		@DeleteMapping("/{id}")
		public ResponseEntity<String> deleteUser(@PathVariable @Positive long id) {

			Optional<User> user = services.getUser(id);
			if (user.isPresent()) {
				services.Delete(id);
				return new ResponseEntity<>("Deleted successfully", HttpStatus.ACCEPTED);
			}
			throw new RecordNotFoundException("Invalid user id : " + id);

		}

		@PatchMapping("/{id}")
		public ResponseEntity<String> patchUser(@RequestBody User user, @PathVariable @Positive long id) {

			Optional<User> user1 = services.getUser(id);
			if (!user1.isPresent()) {
				throw new RecordNotFoundException("Invalid user id : " + id);
			}

			services.updateUser(user, user1.get());
			return new ResponseEntity<>("updated successfully", HttpStatus.CREATED);

		}
		
		@GetMapping("username/{userName}")
		public ResponseEntity<User> getUser(@PathVariable String userName) {

			Optional<User> user = services.getByUserName(userName);
			if (user.isPresent()) {
				return new ResponseEntity<>(services.getByUserName(userName).get(), HttpStatus.OK);
			}

			throw new RecordNotFoundException("Invalid userName : " + userName);

		}
		
		@GetMapping("dob/{dob}")
		public ResponseEntity<User> getUserByDob(@PathVariable String dob) {
			
			

			Optional<User> user = services.getByDob(dob);
			if (user.isPresent()) {
				return new ResponseEntity<>(services.getByDob(dob).get(), HttpStatus.OK);
			}

			throw new RecordNotFoundException("No user with this dob : " + dob);

		}
		

		@ResponseStatus(HttpStatus.BAD_REQUEST)
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
			Map<String, String> errors = new HashMap<>();

			ex.getBindingResult().getFieldErrors()
					.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

			return errors;
		}

}

