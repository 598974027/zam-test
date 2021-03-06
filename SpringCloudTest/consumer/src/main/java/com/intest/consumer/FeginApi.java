package com.intest.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "zam-producer", fallback = FeginApiImpl.class)
public interface FeginApi {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello();

}
