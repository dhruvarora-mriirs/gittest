package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

	@RequestMapping("/mongoFallback")
	public Mono<String> mongoFallback(){ 
	return Mono.just("mongo service is down , please try again later");
			}
	
	@RequestMapping("/weatherFallback")
	public Mono<String> weatherFallback(){ 
	return Mono.just("weather service is down , please try again later");
			}
}
