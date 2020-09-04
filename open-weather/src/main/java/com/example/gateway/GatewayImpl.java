package com.example.gateway;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.client.OpenWeatherClient;
import com.example.model.CurrentWeatherResponse;

import retrofit2.Call;
import retrofit2.Response;

@Service
public class GatewayImpl {

	@Autowired
	private OpenWeatherClient opClient;
	
	public  CurrentWeatherResponse getCurrentWeather(String appid,String exclude,double lat,double lon) throws IOException {
		 Call<CurrentWeatherResponse> callSync = opClient.getCurrentWeather(appid, exclude, lat, lon);
       
	            Response<CurrentWeatherResponse> response = callSync.execute();
	            if (response.code()==200) {
	            	return response.body();
	            }
	            throw new IOException(response.error(response.code(), response.errorBody()) != null
		                    ? response.errorBody().string() + response.code() : "Unknown error");
	         
	            }

	public CurrentWeatherResponse getWeatherByCity(String appid, String q) throws IOException {
		Call<CurrentWeatherResponse> callSync = opClient.getWeatherByCity(appid,q);
	       
        Response<CurrentWeatherResponse> response = callSync.execute();
        if (response.code()==200) {
        	return response.body();
        }
        else {
        	 int errorCode = response.code();
        	 String message=response.errorBody().string();
        throw new IOException(response.error(response.code(), response.errorBody()) != null
                    ? response.errorBody().string() + response.code() : "Unknown error");
        }
        }
	          
}
	
	

