package com.mj.demo.eurekacustomer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("customer")
public class CustomController {


    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/test")
    public String testClient() {

        String result = restTemplate.getForObject("http://eureka-client/eureka-client/test/sayClient", String.class);
        System.out.println("customer result:"+result);
        System.out.println("customer body result:"+result);
        return result;
    }
}
