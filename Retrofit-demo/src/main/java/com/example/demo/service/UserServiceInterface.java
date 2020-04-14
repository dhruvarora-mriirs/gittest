package com.example.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.models.UserApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

@Component
public interface UserServiceInterface {
	

    @GET("/api/users/{id}")
    public Call<UserApiResponse> getUser(@Path("id") long id);

}
