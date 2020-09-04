package com.example.service;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.gateway.GatewayImpl;
import com.example.model.CurrentWeatherResponse;
import com.example.model.PageRequest;

@Service
public class WeatherService {

	@Autowired
	private GatewayImpl services;
	
	
	public CurrentWeatherResponse getCurrentWeather  (PageRequest pageRequest)
	{
		CurrentWeatherResponse res = null;
		try {
		res=services.getCurrentWeather(pageRequest.getAPPID(), pageRequest.getExclude(), pageRequest.getLat(), pageRequest.getLon());
		return res;
		} catch (IOException e) {
			
			e.printStackTrace();
		
		}
		return res;
	
	}


	public CurrentWeatherResponse getWeatherByCity(PageRequest pageRequest) {
		
		CurrentWeatherResponse res = null;
		try {
		res=services.getWeatherByCity(pageRequest.getAPPID(), pageRequest.getQ());
	} 
		catch(IOException e) {
		
		e.printStackTrace();
	
	}
	return res;
   }
}
