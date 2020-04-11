package com.example.web_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling//定时任务
@EnableAsync//异步调用
@EnableCaching//缓存
@MapperScan("com.example.web_demo.mybatis")
@ComponentScan("com.example")
//如果不使用@MapperScan注解，还可以在每个 mapper 接口类上加上 @Mapper 这个注解，但是这样做比较麻烦，
//如果所有的mapper接口类都在一个包下，还是使用@MapperScan注解更为方便
public class WebDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebDemoApplication.class, args);
    }

}
