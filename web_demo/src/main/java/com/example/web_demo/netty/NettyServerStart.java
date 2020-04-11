package com.example.web_demo.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/30 19:38
 * @see
 **/
@Component
public class NettyServerStart implements CommandLineRunner {

    @Autowired
    private NettyServer nettyServer;

    @Override
    @Async//使用了@Async的方法，会被当成是一个子线程
    public void run(String... args) throws Exception {
//        nettyServer.start();
    }

}
