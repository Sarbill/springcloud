package com.mj.demo.eurekaclient1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
public class ClientController {

    @GetMapping(value = "sayClient")
    public String sayClient() {

        System.out.println("this is eureka-client");
        return "this is eureka-client";
    }
}
