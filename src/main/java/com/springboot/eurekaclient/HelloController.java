package com.springboot.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient discoveryClient;

    public URI serviceUrl() {
        List<ServiceInstance> list = discoveryClient.getInstances("spring-cloud-eureka-client");
        if (list != null && list.size() > 0 ) {
            return list.get(0).getUri();
        }
        return null;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String greeting() {
        return "Hello from Eureka Client";
    }

}
