package com.example.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class RefreshController {

	@GetMapping("/refresh")
	public ResponseEntity<?> refreshScope() {
		return ResponseEntity.ok("Refresh Successfully done!!");
	}

}
