package com.mj.demo.eurekaclient.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
public class ClientController {




    @GetMapping(value = "sayClient/{word}")
    public String sayClient(@PathVariable String word) {

        System.out.println("this is eureka-client1:"+word);
        return "this is eureka-client1:"+word;
    }
}
