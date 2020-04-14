package com.example.demo.gateway;

import java.io.IOException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.configuration.UserConfiguration;
import com.example.demo.models.UserApiResponse;
import com.example.demo.service.UserServiceInterface;

import retrofit2.Call;
import retrofit2.Response;

@Component
public class ApiGateway {
	
	@Autowired
	private UserServiceInterface services;


	public UserApiResponse getRepositories(long id) throws IOException {
		 Call<UserApiResponse> callSync = services.getUser(id);
        
	            Response<UserApiResponse> response = callSync.execute();
	            if (!response.isSuccessful()) {
		            throw new IOException(response.errorBody() != null
		                    ? response.errorBody().string() : "Unknown error");}
	            
	           return response.body();
	        }


       

}
