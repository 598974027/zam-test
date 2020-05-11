package com.intest.consumer;

import org.springframework.stereotype.Component;

@Component
public class FeginFailBackImpl implements FeginApi {

    @Override
    public String hello() {
        return "fegin熔断处理";
    }

}
