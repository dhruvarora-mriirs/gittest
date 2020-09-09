package com.example.client;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.model.AqiResponse;
import com.example.model.Response.AqiFeedResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

@Component
public interface AqicnClient {

	
	@GET("/feed/{city}")
	public Call<AqiFeedResponse> getAqiFeedByCityPath( @Path(value="city") String city, @Query("token") String token);
	
	
	@GET("/search/")
	public Call<AqiResponse> getAqiBySearch(@Query("keyword") String keyword,@Query("token") String token);
}
