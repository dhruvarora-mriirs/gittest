package com.example.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
@Order(1)
public class JWTValidationPreFilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(JWTValidationPreFilter.class);

	
		
		@Override
		  public String filterType() {
		    return "post";
		  }

		  @Override
		  public int filterOrder() {
		    return 1;
		  }

		  @Override
		  public boolean shouldFilter() {
			  return true;
		  }

		  @Override
		  public Object run() {
			  RequestContext ctx = RequestContext.getCurrentContext();
			    HttpServletRequest request = ctx.getRequest();
		    HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
		    RequestContext ctxreq = RequestContext.getCurrentContext();
		    
		
		    
		   if(request.getHeader("Authorization")==null &&  !(request.getRequestURL().toString().equalsIgnoreCase("http://localhost:8091/authenticate")||request.getRequestURL().toString().equalsIgnoreCase("http://localhost:8091/register"))) {
	    		
	    	    try {
	    	    	response.sendError(401,"you must be login to use the service");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		    
		    else
		    {
		    	String[] split_string1 = request.getHeader("Authorization").split(" ");
		        
		    	String base64EncodedJWT = (String)split_string1[1];
		        String[] splitString2 = base64EncodedJWT.split("\\.");
		        String base64EncodedPayload = splitString2[1];
		        Base64 base64Url = new Base64(true);
		        String header = new String(base64Url.decode(base64EncodedPayload));
		        String[] payloadArray = header.split(":");
		        String unameextra = payloadArray[1];
		        String[] unamearray = unameextra.split(",");
		        String uname = unamearray[0];
		        int length = uname.length();
		        String unameOriginal = uname.substring(1,length-1);

		        String[] requestJwtToken =  request.getHeader("Authorization").split(" ");
		        String requestJwtTokenPart = requestJwtToken[1];
		        
		        String redisJwtTokenUrl = "http://localhost:8091/verify";
		        System.out.println(redisJwtTokenUrl);
		        String serverUrl="http://localhost:8091/verify";
		      //  String search=URLEncoder.encode("={\"ABC.CP\":\"12345\"}");
		        try {    URL url = new URL(serverUrl);
		            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		            httpCon.setDoOutput(true);
		         
						httpCon.setRequestMethod("POST");
				
		            httpCon.getHeaderField("split_string1");
		     
		                //FAILS GIVING 405 STATUS CODE                       
		            int responseCode = httpCon.getResponseCode();
		      
		    		OutputStream out = httpCon.getOutputStream();
			
		        } 
		        
		        
		        catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  } return null;
		  }
		  }
	