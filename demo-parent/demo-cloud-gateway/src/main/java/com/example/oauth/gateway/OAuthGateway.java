package com.example.oauth.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.exception.GatewayException;
import com.example.model.Token;
import com.example.oauth.client.OAuthClient;

import retrofit2.Response;

@Service
public class OAuthGateway {

	@Autowired
	private OAuthClient authClient;
	
	public Token verifyAndDecodeAccessToken(Token token) {
		Response<Token> response = authClient.verifyAndDecodeAccessToken(token).blockingGet();
		if (!response.isSuccessful()) {
			throw new GatewayException(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);
		}
		return response.body();
	}

	public Token generateAccessToken(Token token) {
		Response<Token> response = authClient.generateAccessToken(token).blockingGet();
		if (!response.isSuccessful()) {
			throw new GatewayException(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);
		}
		return response.body();
	}

	public Token generateAuthToken(Token token) {
		Response<Token> response = authClient.generateAuthToken(token).blockingGet();
		if (!response.isSuccessful()) {
			throw new GatewayException(HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);
		}
		return response.body();
	}

	public Token verifyAndDecodeAuthToken(Token token) {
		final Response<Token> response = authClient.verifyAndDecodeAuthToken(token).blockingGet();
		if (!response.isSuccessful()) {
			return null;
		}
		return response.body();
	}
	 
}
