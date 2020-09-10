package com.example.filter;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.isomorphism.util.TokenBucket;
import org.isomorphism.util.TokenBuckets;

//import org.misomorphism.util.TokenBucket;
import reactor.core.publisher.Mono;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * Sample throttling filter. See https://github.com/bbeck/token-bucket
 */

public class ThrottleFilter implements GatewayFilter {

	private static final Log log = LogFactory.getLog(ThrottleFilter.class);

	int capacity;

	int refillTokens;

	int refillPeriod;

	TimeUnit refillUnit;

	public int getCapacity() {
		return capacity;
	}

	public ThrottleFilter setCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public int getRefillTokens() {
		return refillTokens;
	}

	public ThrottleFilter setRefillTokens(int refillTokens) {
		this.refillTokens = refillTokens;
		return this;
	}

	public int getRefillPeriod() {
		return refillPeriod;
	}

	public ThrottleFilter setRefillPeriod(int refillPeriod) {
		this.refillPeriod = refillPeriod;
		return this;
	}

	public TimeUnit getRefillUnit() {
		return refillUnit;
	}

	public ThrottleFilter setRefillUnit(TimeUnit refillUnit) {
		this.refillUnit = refillUnit;
		return this;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		TokenBucket tokenBucket = TokenBuckets.builder().withCapacity(capacity)
				.withFixedIntervalRefillStrategy(refillTokens, refillPeriod, refillUnit)
				.build();

		// TODO: get a token bucket for a key
		log.debug("TokenBucket capacity: " + tokenBucket.getCapacity());
		boolean consumed = tokenBucket.tryConsume();
		if (consumed) {
			return chain.filter(exchange);
		}
		exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
		return exchange.getResponse().setComplete();
	}

}