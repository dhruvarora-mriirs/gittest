package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.model.Users;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RefreshScope
@RestController
public class UserDashBoardController {


	    @Autowired
	    private RestTemplate restTemplate;

	    @Autowired
	    private EurekaClient eurekaClient;

	    @Value("${service.user.serviceId}")
	    private String userSearchServiceId;

	    @RequestMapping("/dashboard/{myself}")
	    public Users findme(@PathVariable Long myself) {

	        Application application = eurekaClient.getApplication(userSearchServiceId);

	        InstanceInfo instanceInfo = application.getInstances().get(0);

	        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "user/" + myself;

	        System.out.println("URL" + url);

	        Users emp = restTemplate.getForObject(url, Users.class);

	        System.out.println("RESPONSE " + emp);

	        return emp;

	    }
	    @RequestMapping("/sample")
	    public ResponseEntity<?> getdata()
	    {
	        
	    	return new ResponseEntity<>(restTemplate.getForObject("http://localhost:8081/api/v1/user",Collection.class),HttpStatus.OK);
	    }

	    @RequestMapping("/dashboard/peers")

	    public Collection < Users > findPeers() {

	        Application application = eurekaClient.getApplication(userSearchServiceId);

	        InstanceInfo instanceInfo = application.getInstances().get(0);

	        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "users";

	        System.out.println("URL" + url);

	        Collection < Users > list = restTemplate.getForObject(url, Collection.class);

	        System.out.println("RESPONSE " + list);

	        return list;

	    }
}
