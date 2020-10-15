package com.example.oauth.client;


import org.springframework.stereotype.Component;

import com.example.model.Token;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

@Component
public interface OAuthClient {

	@POST("/api/v1/tokens/access/decode")
	Single<Response<Token>> verifyAndDecodeAccessToken(@Body Token token);

	@POST("/api/v1/tokens/access")
	Single<Response<Token>> generateAccessToken(@Body Token token);

	@POST("/api/v1/tokens")
	Single<Response<Token>> generateAuthToken(@Body Token token);

	@POST("/api/v1/tokens/verify")
	Single<Response<Token>> verifyAndDecodeAuthToken(@Body Token token);
}
