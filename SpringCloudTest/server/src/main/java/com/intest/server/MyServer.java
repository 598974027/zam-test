package com.intest.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//eureka服务端
@EnableEurekaServer
public class MyServer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MyServer.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyServer.class, args);
//        SpringApplication application = new SpringApplication(MyServer.class);
//        application.setBannerMode(Banner.Mode.OFF);
//        application.run(args);
    }

}
