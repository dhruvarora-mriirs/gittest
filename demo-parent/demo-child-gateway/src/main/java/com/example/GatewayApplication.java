package com.example;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.config.SecurityConfig;
import com.example.filter.ErrorFilter;
//import com.example.filter.JWTValidationPreFilter;
import com.example.filter.PostFilter;
import com.example.filter.PreFilter;
import com.example.filter.RouteFilter;

@EnableDiscoveryClient
@EnableZuulProxy
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(SecurityConfig.class)
@SpringBootApplication
public class GatewayApplication {

	
	public static void main(String args[])
	{
		SpringApplication.run(GatewayApplication.class, args);	
	}
	
	@Resource 
	private SecurityConfig securityConfig;
	
//    
//    @Bean
//    public JWTValidationPreFilter authenticationFilter() {
//    	return new JWTValidationPreFilter();
//    }
//    

	
}
