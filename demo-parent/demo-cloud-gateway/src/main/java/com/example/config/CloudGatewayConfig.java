package com.example.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.http.HttpMethod;

import com.example.filter.AuthFilter;
import com.example.filter.AuthFilter.Config;
import com.example.filter.ThrottleFilter;

@Configuration
public class CloudGatewayConfig {

	@Autowired
	private AuthFilter authFilter;
	
	@Autowired
	private ThrottleFilter throttle;

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		Config config =new Config();
		return builder.routes()
				
			  // user login	
                 .route(r -> r.path("/api/v1/user/login").and().method(HttpMethod.POST)
						
						.filters(f -> f
						        .hystrix(h -> h.setName("Hystrix")
		                				.setFallbackUri("forward:/fallback"))
						        .filter(new ThrottleFilter().setCapacity(1)
						        		.setRefillPeriod(1)
						        		.setRefillTokens(10)
						        		.setRefillUnit(TimeUnit.SECONDS))
						       
						)
						
						.uri("lb://hotel-service")
						.id("userlogin"))
             	
                 //hotel-service user register	
                 .route(r -> r.path("/api/v1/user").and().method(HttpMethod.POST)
						
						.filters(f -> f
						        .hystrix(h -> h.setName("Hystrix")
		                				.setFallbackUri("forward:/fallback"))
						        .filter(new ThrottleFilter().setCapacity(1)
						        		.setRefillPeriod(1)
						        		.setRefillTokens(10)
						        		.setRefillUnit(TimeUnit.SECONDS))
						)
						.uri("lb://hotel-service")
						.id("userregister"))
				
                 //hotel-service - user api 
				.route(r -> r.path("/api/v1/user/**")
				
						.filters(f -> f.filter(authFilter.apply(config))
						        .hystrix(h -> h.setName("Hystrix")
		                				.setFallbackUri("forward:/fallback"))
						        .filter(new ThrottleFilter().setCapacity(1)
						        		.setRefillPeriod(1)
						        		.setRefillTokens(10)
						        		.setRefillUnit(TimeUnit.SECONDS))
						                
						)
						.uri("lb://hotel-service")
						.order(1)
						.id("userreservations"))
				
				
				.build();
	}

}