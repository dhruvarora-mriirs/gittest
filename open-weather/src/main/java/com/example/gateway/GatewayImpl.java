package com.example.gateway;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.client.AqicnClient;
import com.example.client.OpenWeatherClient;
import com.example.model.CurrentWeatherResponse;
import com.example.model.Response.AqiFeedResponse;

import retrofit2.Call;
import retrofit2.Response;

@Service
public class GatewayImpl {

	@Autowired
	private OpenWeatherClient opClient;
	@Autowired
	private AqicnClient aqicnClient;

	public CurrentWeatherResponse getCurrentWeather(String appid, String exclude, double lat, double lon)
			throws IOException {
		Call<CurrentWeatherResponse> callSync = opClient.getCurrentWeather(appid, exclude, lat, lon);

		Response<CurrentWeatherResponse> response = callSync.execute();
		if (response.code() == 200) {
			return response.body();
		}
		throw new IOException(response.error(response.code(), response.errorBody()) != null
				? response.errorBody().string() + response.code()
				: "Unknown error");

	}

	public CurrentWeatherResponse getWeatherByCity(String appid, String q) throws IOException {
		Call<CurrentWeatherResponse> callSync = opClient.getWeatherByCity(appid, q);

		Response<CurrentWeatherResponse> response = callSync.execute();
		if (response.code() == 200) {
			return response.body();
		} else {
			int errorCode = response.code();
			String message = response.errorBody().string();
			throw new IOException(response.error(response.code(), response.errorBody()) != null
					? response.errorBody().string() + response.code()
					: "Unknown error");
		}
	}

	// Gateway implementation for AQICN starts here

	public Object getAqiFeedByCity(String path, String token) throws IOException {
		Call<?> callSync = aqicnClient.getAqiFeedByCityPath(path, token);

		Response<?> response = callSync.execute();
		if (response.code() == 200) {
			return response.body();
		} else {
			int errorCode = response.code();
			String message = response.errorBody().string();
			throw new IOException(response.error(response.code(), response.errorBody()) != null
					? response.errorBody().string() + response.code()
					: "Unknown error");
		}

	}

	public Object getAqiBySearch(String keyword, String token) throws IOException {
		Call<?> callSync = aqicnClient.getAqiBySearch(keyword, token);

		Response<?> response = callSync.execute();
		if (response.code() == 200) {
			return response.body();
		} else {
			int errorCode = response.code();
			String message = response.errorBody().string();
			throw new IOException(response.error(response.code(), response.errorBody()) != null
					? response.errorBody().string() + response.code()
					: "Unknown error");
		}

	}

}
