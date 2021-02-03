package com.intest.consumer;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeginApiFactory implements FallbackFactory<FeginApi> {

    @Override
    public FeginApi create(Throwable throwable) {
        return new FeginApi() {

            @Override
            public String hello() {
                return "fegin降级处理";
            }
        };
    }
}
