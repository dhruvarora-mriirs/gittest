package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

	@RequestMapping("/fallback")
	public Mono<String> mongoFallback() {
		return Mono.just("service is down , please try again later");
	}
}
