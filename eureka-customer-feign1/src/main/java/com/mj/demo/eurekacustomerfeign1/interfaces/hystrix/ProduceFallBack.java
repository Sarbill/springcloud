package com.mj.demo.eurekacustomerfeign1.interfaces.hystrix;

import com.mj.demo.eurekacustomerfeign1.interfaces.Produce;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class ProduceFallBack implements Produce {

    @Override
    public String consumer(@PathVariable String word) {
        System.out.println("fail call back:"+word);
        return "fail call back:"+word;
    }
}
