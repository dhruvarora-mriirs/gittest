package com.example.filter;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.exception.GatewayException;
import com.example.model.Token;
import com.example.oauth.gateway.OAuthGateway;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

	@Autowired
	private OAuthGateway oauth;

	public AuthFilter() {

		super(Config.class);
	}

	private boolean isAuthorizationValid(String authorizationHeader) {
		// Logic for checking the value

		boolean isValid = true;

		Token authToken = new Token(null, null, authorizationHeader, null, null, null, null);

		Token validToken = oauth.verifyAndDecodeAccessToken(authToken);
		if (validToken.equals(null))
			isValid = false;
		return isValid;

	}

	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {

		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(httpStatus);
		return response.setComplete();
	}

	@Override

	public GatewayFilter apply(Config config) {

		return (exchange, chain) -> {

			ServerHttpRequest request = exchange.getRequest();
			if (!request.getHeaders().containsKey("Authorization")) {

				return this.onError(exchange, "No Authorization header", HttpStatus.UNAUTHORIZED);
			}
			String requestTokenHeader = request.getHeaders().getFirst("Authorization");

			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				requestTokenHeader = requestTokenHeader.substring(7);

			} else {
				throw new GatewayException("JWT Token does not begin with Bearer String", HttpStatus.BAD_REQUEST);
			}

			String authorizationHeader = requestTokenHeader;
			if (!this.isAuthorizationValid(authorizationHeader)) {
				return this.onError(exchange, "Invalid Authorization header", HttpStatus.UNAUTHORIZED);
			}
			ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
					.header("secret", RandomStringUtils.random(10)).build();

			return chain.filter(exchange.mutate().request(modifiedRequest).build());
		};
	}

	public static class Config {

		// Put the configuration properties

	}
}