package com.mj.demo.eurekacustomerhystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Customer {


    @Autowired
    ConsumerService consumerService;



    @GetMapping("/testClient")
    public String testClient() {

        String result = consumerService.consumer();

        System.out.println(result);
        return result;
    }

    @Service
    class ConsumerService{

        @Autowired
        RestTemplate restTemplate;

        @Autowired
        LoadBalancerClient loadBalancerClient;

        @HystrixCommand(fallbackMethod = "failCallBack")
        public String consumer() {
            ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
            String result = restTemplate.getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/eureka-client/test/sayClient", String.class);
            System.out.println("customer result:"+result);
            System.out.println("customer body result:"+result);
            return result;
        }

        public String failCallBack() {
            System.out.println("call back fail");

            return "call back fail";
        }
    }
}
