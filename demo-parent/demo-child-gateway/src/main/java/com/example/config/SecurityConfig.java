package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="security")
public class SecurityConfig {
	
	private String sharedSecret;
	
	public String getSharedSecret() {
		return sharedSecret;
	}
	
	public void setSharedSecret(String sharedSecret) {
		this.sharedSecret = sharedSecret;
	}

}

