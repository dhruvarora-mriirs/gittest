package com.example.demo.configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.models.User;
import com.example.demo.service.UserServiceInterface;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;


@Configuration
public class UserConfiguration {

	@Bean
	public UserServiceInterface retrofitConfig(){
	    
		 OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
	    
		 return   new Retrofit.Builder()
	                .baseUrl("https://reqres.in/")
	                .addConverterFactory(GsonConverterFactory.create())
	                .build().create(UserServiceInterface.class);
	        
	        
	}
	       
}
