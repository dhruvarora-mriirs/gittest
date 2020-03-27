package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.filter.CORSFilter;
import com.example.filter.ErrorFilter;
import com.example.filter.PostFilter;
import com.example.filter.PreFilter;
import com.example.filter.RouteFilter;

@Configuration

public class GatewayConfiguration {
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
	
	@Bean 
	public CORSFilter corsFilter()
	{
		
		return new CORSFilter();
	}

}