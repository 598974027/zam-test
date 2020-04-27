package com.intest.producer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//eureka客户端
@EnableEurekaClient
public class Provider {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Provider.class).run(args);
    }

}
