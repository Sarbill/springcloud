package com.mj.demo.eurekacustomer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("customer")
public class CustomController {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;


    @GetMapping("/test/{word}")
    public String testClient(@PathVariable String word) {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("eureka-client");
        System.out.println("===" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":"
                + serviceInstance.getPort()+":"+serviceInstance.getUri());//


        String result = restTemplate.getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/eureka-client/test/sayClient/"+word, String.class);
        System.out.println("customer result:"+result);
        System.out.println("customer body result:"+result);
        return result;
    }
}
