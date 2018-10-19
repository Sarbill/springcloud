package com.mj.demo.eurekacustomerhystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Customer {


    @Autowired
    ConsumerService consumerService;



    @GetMapping("/testClient/{word}")
    public String testClient(@PathVariable String word) {

        String result = consumerService.consumer(word);

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
        public String consumer(String word) {
            ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
            String result = restTemplate.getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/eureka-client/test/sayClient/{word}", String.class,word);
            System.out.println("customer result:"+result);
            System.out.println("customer body result:"+result);
            return result;
        }

        public String failCallBack(String word) {
            System.out.println("call back fail:"+word);

            return "call back fail";
        }
    }
}
