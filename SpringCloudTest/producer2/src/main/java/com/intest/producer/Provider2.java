package com.intest.producer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Provider2 {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Provider2.class).run(args);
    }

}
