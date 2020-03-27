package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
