package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.gateway.ApiGateway;
import com.example.demo.models.UserApiResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Service
public class UserService {
 
	@Autowired
	ApiGateway gateway;
	
	public UserApiResponse getRepositories(long id) throws IOException {
		
		return gateway.getRepositories(id);
			
		}
	
}

