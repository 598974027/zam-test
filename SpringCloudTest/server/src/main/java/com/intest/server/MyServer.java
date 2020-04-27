package com.intest.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//eureka服务端
@EnableEurekaServer
public class MyServer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MyServer.class).run(args);
    }

}
