package com.intest.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class Controller {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private FeginApi feginApi;

    /**
     * ribbon负载均衡调用
     *
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        ResponseEntity<String> responseEntity = getRestTemplate().getForEntity("http://producer-zam/hello", String.class);
        return responseEntity.getBody();
    }

    /**
     * fegin负载均衡调用
     *
     * @return
     */
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2() {
        return feginApi.hello();
    }

}
