package com.example.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.example.exceptions.RecordNotFoundException;
import com.example.interfaces.Throttle;
import com.example.model.Users;
import com.example.service.UserService;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.distribution.Histogram;
import net.bytebuddy.asm.Advice.Thrown;


@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Throttle(maxPerSecond = 1, maxPerMinute = 100, maxPerHour = 1000, maxPerDay = 10000)
	@RequestMapping("/hello-world/{name}")
	public String getHelloWorld (@PathVariable String name)
	{
		return "Hello World " + name;
	}

	
	@Throttle(maxPerSecond = 10, maxPerMinute = 100, maxPerHour = 1000, maxPerDay = 10000)
	@Timed(value="userInfo.gettingAll.request",
		       histogram=true,
		       percentiles = {0.95,0.99},
		       extraTags = {"version","1.0"})
@RequestMapping(value = "/users", method = RequestMethod.GET)
@ReadOperation
public ResponseEntity<List<Users>> getAllUser(
                    @RequestParam(defaultValue = "0") Integer pageNo, 
                    @RequestParam(defaultValue = "10") Integer pageSize,
                    @RequestParam(defaultValue = "id") String sortBy) 

{
    List<Users> list = userService.getAllUsers(pageNo, pageSize, sortBy);
    if(list.isEmpty())
    return new ResponseEntity<List<Users>>(list, new HttpHeaders(), HttpStatus.NO_CONTENT); 
    
    return new ResponseEntity<List<Users>>(list, new HttpHeaders(), HttpStatus.OK);


}



@Timed(value="userInfo.getting.request",
       histogram=true,
       percentiles = {0.95,0.99},
       extraTags = {"version","1.0"}
       )

@Throttle(maxPerSecond = 1, maxPerMinute = 100, maxPerHour = 1000, maxPerDay = 10000)
@GetMapping("user/{id}")
public ResponseEntity<Users> getUser(@PathVariable @Positive long id) {

Optional<Users> user = userService.getUser(id);
if (user.isPresent()) {
	return new ResponseEntity<>(userService.getUser(id).get(), HttpStatus.OK);
}

throw new RecordNotFoundException("Invalid user id : " + id);

}

@Timed(value="userInfo.postUser.request",
histogram=true,
percentiles = {0.95,0.99},
extraTags = {"version","1.0"})
@PostMapping
@WriteOperation
public ResponseEntity<Users> addUser(@Valid @RequestBody Users user)throws Exception {
 

    
	return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
  
}

@Timed(value="userInfo.updateUser.request",
histogram=true,
percentiles = {0.95,0.99},
extraTags = {"version","1.0"})

@PutMapping("user/{id}")
@WriteOperation
public ResponseEntity<String> updateUser(@Valid @RequestBody Users user) {
long id = user.getId();
Optional<Users> user1 = userService.getUser(id);
if (user1.isPresent()) {
	userService.saveUser(user);
	return ResponseEntity.status(HttpStatus.OK).body("user found");
}
throw new RecordNotFoundException("Invalid user id : " + id);
}

@Timed(value="userInfo.deleteUser.request",
histogram=true,
percentiles = {0.95,0.99},
extraTags = {"version","1.0"})
@DeleteMapping("user/{id}")
@DeleteOperation
public ResponseEntity<String> deleteUser(@PathVariable @Positive long id) {

Optional<Users> user = userService.getUser(id);
if (user.isPresent()) {
	userService.Delete(id);
	return new ResponseEntity<>("Deleted successfully", HttpStatus.ACCEPTED);
}
throw new RecordNotFoundException("Invalid user id : " + id);

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

