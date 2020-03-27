package com.example.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.statsd.StatsdConfig;
import io.micrometer.statsd.StatsdFlavor;
import io.micrometer.statsd.StatsdMeterRegistry;


@Configuration
public class MicrometerConfiguration {

	
	@Bean
	MeterRegistryCustomizer meterRegistryCustomizer(MeterRegistry meterRegistry) {
		
		//List<String> list = meterRegistry.gauge("listGauge", Collections.emptyList(), new ArrayList<>(), List::size);
		return meterRegistry1->{
			meterRegistry.config()
			.commonTags("application","micrometer-hello-example");
			
		};
	}

	StatsdConfig config = new StatsdConfig() {
	    @Override
	    public String get(String k) {
	        return null;
	    }

	    @Override
	    public StatsdFlavor flavor() {
	        return StatsdFlavor.TELEGRAF;
	    }
	};

	MeterRegistry registry = new StatsdMeterRegistry(config, Clock.SYSTEM);
	
	  @Bean
	   public StatsDClient statsDClient(
	           @Value("${metrics.statsd.host:localhost}") String host,
	           @Value("${metrics.statsd.port:8125}") int port,
	           @Value("${metrics.prefix:example.app}") String prefix
	   ) {
	       return new NonBlockingStatsDClient(prefix, host, port);
	   }
}
