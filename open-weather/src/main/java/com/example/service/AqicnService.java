package com.example.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.RandomException;
import com.example.gateway.GatewayImpl;
import com.example.model.AqiPageRequest;
import com.example.model.CurrentWeatherResponse;
import com.example.model.PageRequest;

@Service
public class AqicnService {

	@Autowired
	private GatewayImpl services;

	public  Object getAqiFeedByCity(AqiPageRequest pageRequest) {
		Object res = null;
		try {
		res=services.getAqiFeedByCity(pageRequest.getPath(),pageRequest.getToken());
		return res;
		} catch (IOException e) {
			
			e.printStackTrace();
		
		}
		return res;
	}

	public Object getAqiBySearch(AqiPageRequest pageRequest) {
		try {
			return services.getAqiBySearch(pageRequest.getKeyword(), pageRequest.getToken());
		} catch (IOException e) {
			throw new RandomException("Error occured");
		}
	}
	
	
}
