package com.intest.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Configuration
public class Controller {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Resource
    private FeginApi feginApi;

    /**
     * ribbon负载均衡调用
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "getHystrixFailBack")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        ResponseEntity<String> responseEntity = getRestTemplate().getForEntity("http://producer-zam/hello", String.class);
        return responseEntity.getBody();
    }

    /**
     * 指定的备用方法和原方法的参数要相同
     *
     * @return
     */
    public String getHystrixFailBack() {
        return "ribbon熔断处理";
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
