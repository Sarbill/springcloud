package com.mj.demo.eurekacustomerfeign.interfaces;


import com.mj.demo.eurekacustomerfeign.interfaces.hystrix.ProduceFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( value = "eureka-client",fallback = ProduceFallBack.class)
public interface Produce {


    @GetMapping(value = "/eureka-client/test/sayClient/{word}")
    String consumer(@PathVariable(value = "word") String word);//带有参数的调用，此处必须加上value,否则会调用失败。eg:@RequestParam(value="xxx"),@RequestBody User user
}
