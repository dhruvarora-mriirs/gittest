package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.interfaces.Throttle;
import com.example.model.ApiCall;
import com.example.model.MailRequest;
import com.example.repositories.ApiCallRepository;

@Aspect
@Component
public class ThrottleAspect {
	 private final ApiCallRepository apiCallRepository;

	    private final static int SECOND_PERIOD = -1;
	    private final static int MINUTE_PERIOD = -60;
	    private final static int HOUR_PERIOD = -3600;
	    private final static int DAY_PERIOD = -86400;

	    @Value("${throttle.maxPerSecond}")
	    private int perSecondProperties;

	    @Value("${throttle.maxPerMinute}")
	    private int perMinuteProperties;

	    @Value("${throttle.maxPerHour}")
	    private int perHourProperties;

	    @Value("${throttle.maxPerDay}")
	    private int perDayProperties;

	    @Autowired
	    public ThrottleAspect(ApiCallRepository apiCallRepository) {
	        this.apiCallRepository = apiCallRepository;
	    }
	    
	    @Pointcut(value = "execution(* *.*(..)) && " +
	            "@annotation(com.example.interfaces.Throttle)")
	    private void checkApiPointCut() {

	    }

	    @Before(value = "checkApiPointCut()")
	    private void checkApiRates(JoinPoint joinPoint) {
	        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
	        Method method = signature.getMethod();
	        Throttle throttle = method.getAnnotation(Throttle.class);
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String serializationKey = null;
	        if (authentication.isAuthenticated()) {
	            serializationKey = authentication.getName();
	        }
	        if (serializationKey == null) {
	            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	            serializationKey = request.getRemoteAddr();
	        }
	        int maxPerSecond = throttle.maxPerSecond();
	        int maxPerMinute = throttle.maxPerMinute();
	        int maxPerHour = throttle.maxPerHour();
	        int maxPerDay = throttle.maxPerDay();
	        this.checkCalls(maxPerSecond, serializationKey, SECOND_PERIOD);
	        this.checkCalls(maxPerMinute, serializationKey, MINUTE_PERIOD);
	        this.checkCalls(maxPerHour, serializationKey, HOUR_PERIOD);
	        this.checkCalls(maxPerDay, serializationKey, DAY_PERIOD);
	        apiCallRepository.save(new ApiCall(serializationKey, new Date().getTime()));
	    }

	    private void checkCalls(int max, String apiKey, int since) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.SECOND, since);
	        List<ApiCall> calls = apiCallRepository
	                .findAllByTimeStampGreaterThanAndTimeStampLessThanAndKeyy(calendar.getTime().getTime(), new Date().getTime(), apiKey);
	        if (calls.size() + 1 > max) {
	        	
	        	RestTemplate restTemplate=new RestTemplate();
	        	MailRequest request=new MailRequest("dhruv","recieverEmail@gmail.com","senderEmail@gmail.com","Warning");
	        	restTemplate.postForObject("http://localhost:8082/sendingEmail",request,MailRequest.class);
	        	
	        	throw new HttpServerErrorException(HttpStatus.TOO_MANY_REQUESTS);
	        }
			
	    }

}
