package com.intest.consumer;

import org.springframework.stereotype.Component;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2020/4/27 16:52
 * @see
 **/
@Component
public class FeginFailBackImpl implements FeginApi {

    @Override
    public String hello() {
        return "fegin熔断处理";
    }

}
