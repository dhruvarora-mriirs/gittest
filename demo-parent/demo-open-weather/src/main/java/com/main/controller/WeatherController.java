package com.main.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.CurrentWeatherResponse;
import com.main.model.PageRequest;
import com.main.service.WeatherService;


@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {
	@Autowired
	private WeatherService service;
	
	//get current and weekly weather by coordinates.
	@GetMapping
	public ResponseEntity<?> getCurrentWeather(PageRequest pageRequest)
	{	CurrentWeatherResponse response=service.getCurrentWeather(pageRequest);
		
		if(Objects.nonNull(response))
		   return new ResponseEntity<>(response,HttpStatus.OK);
		return new ResponseEntity<>("No content",HttpStatus.NO_CONTENT);
	}
	
	
	//get weather by city name
	@GetMapping("/byCity")
	public ResponseEntity<?> getWeatherByCity(PageRequest pageRequest)
	{	CurrentWeatherResponse response=service.getWeatherByCity(pageRequest);
	
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
