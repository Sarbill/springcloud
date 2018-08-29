package com.mj.demo.consul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class ClientController {


    @GetMapping("sayConsul")
    public String sayConsul(){

        System.out.println("this is consul client");

        return "this is consul client";
    }
}
