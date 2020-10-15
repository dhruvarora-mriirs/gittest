package com.example.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
@EnableWebFlux
public class CORSFilter implements WebFluxConfigurer {

	   @Override
	   public void addCorsMappings(CorsRegistry registry) {
	      registry.addMapping("/**")
	            .allowCredentials(true)
	            .allowedOrigins("*")
	            .allowedHeaders("*")
	            .allowedMethods("*");
	             
	   }

	   @Bean
	   public CorsWebFilter corsWebFilter() {
	      CorsConfiguration corsConfiguration = new CorsConfiguration();
	      corsConfiguration.setAllowCredentials(true);
	      corsConfiguration.addAllowedHeader("*");
	      corsConfiguration.addAllowedMethod("*");
	      corsConfiguration.addAllowedOrigin("*");
	      UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
	      corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
	      return new CorsWebFilter(corsConfigurationSource);
	   }
	
}

