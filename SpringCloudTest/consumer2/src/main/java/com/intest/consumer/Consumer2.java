package com.intest.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Consumer2 {

    public static void main(String[] args) {
        SpringApplication.run(Consumer2.class, args);
    }

}
