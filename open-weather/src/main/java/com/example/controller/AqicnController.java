package com.example.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.AqiPageRequest;
import com.example.model.CurrentWeatherResponse;
import com.example.model.PageRequest;
import com.example.service.AqicnService;

@RestController
@RequestMapping("/api/v1/aqi")
public class AqicnController {
	@Autowired
	private AqicnService service;
	
	//get current and weekly weather by coordinates.
	@GetMapping("/path")
	public ResponseEntity<?> getAqiFeedByCityPath(AqiPageRequest pageRequest)
	{	Object response=service.getAqiFeedByCity(pageRequest);
		
		if(Objects.nonNull(response))
		   return new ResponseEntity<>(response,HttpStatus.OK);
		return new ResponseEntity<>("No content",HttpStatus.NO_CONTENT);
	}
	
	
	//get weather by city name
	@GetMapping("/search")
	public ResponseEntity<?> getAqiBySearch(AqiPageRequest pageRequest)
	{	Object response=service.getAqiBySearch(pageRequest);
	
	if(Objects.nonNull(response))
	   return new ResponseEntity<>(response,HttpStatus.OK);
	return new ResponseEntity<>("No content",HttpStatus.NO_CONTENT);
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
