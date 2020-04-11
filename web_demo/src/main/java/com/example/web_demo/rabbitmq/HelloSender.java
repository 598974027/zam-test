package com.example.web_demo.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/29 9:09
 * @see
 **/
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + LocalDateTime.now();
        this.rabbitTemplate.convertAndSend("exchange3", context);
    }

}