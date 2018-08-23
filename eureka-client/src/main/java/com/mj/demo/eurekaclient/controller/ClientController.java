package com.mj.demo.eurekaclient.controller;


import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {




    @GetMapping(value = "sayClient")
    public String sayClient() {

        System.out.println("this is eureka-client");
        return "this is eureka-client";
    }
}
