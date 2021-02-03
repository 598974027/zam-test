package com.intest.consumer;

import org.springframework.stereotype.Component;

@Component
public class FeginApiImpl implements FeginApi {

    /**
     * fegin负载均衡调用 容错处理方法
     *
     * @return
     */
    @Override
    public String hello() {
        return "fegin熔断处理";
    }

}
