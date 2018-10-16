package com.mj.demo.eurekacustomerfeign.controller;


import com.mj.demo.eurekacustomerfeign.interfaces.Produce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feigntest")
public class FeignController {

    @Autowired
    private Produce produce;

    @GetMapping("sayFeign/{word}")
    public String sayFeign(@PathVariable String word) {

        String result = produce.consumer( word);
        System.out.println(result);
        return result;
    }
}
