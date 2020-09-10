package com.main.client;

import org.springframework.stereotype.Component;

import com.main.model.CurrentWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

@Component
public interface OpenWeatherClient {

	
//get weather by latitude and longitude	daily 

	@GET("/data/2.5/onecall")
	public Call <CurrentWeatherResponse> getCurrentWeather(@Query("appid") String appid,@Query("exclude") String exclude,@Query("lat")double lat,@Query("lon")double lon);

	@GET("/data/2.5/weather")
	public Call<CurrentWeatherResponse> getWeatherByCity(@Query("appid") String appid,@Query("q") String q);

}


