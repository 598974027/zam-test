package com.example.web_demo.redis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/6/1 14:29
 * @see
 **/
@Component
@Order(value = 1)
public class RedisTest implements CommandLineRunner {

    @Resource
    private StringRedisTemplate xxxx;

    @Override
    public void run(String... args) throws Exception {
//        this.xxxx.opsForValue().set("redis", "123456");
//        this.xxxx.opsForHash().put("hash", "123456", "123456");
    }

}