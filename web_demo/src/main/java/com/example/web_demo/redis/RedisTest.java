//package com.example.web_demo.redis;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Component
//public class RedisTest implements CommandLineRunner {
//
//    @Resource
//    private StringRedisTemplate xxxx;
//
//    @Override
//    public void run(String... args) throws Exception {
//        this.xxxx.opsForValue().set("redis", "123456");
//        this.xxxx.opsForHash().put("hash", "123456", "123456");
//    }
//
//}