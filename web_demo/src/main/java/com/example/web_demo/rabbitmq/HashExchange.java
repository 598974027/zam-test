package com.example.web_demo.rabbitmq;

import org.springframework.amqp.core.AbstractExchange;

import java.util.Map;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/30 11:48
 * @see
 **/
public class HashExchange extends AbstractExchange {

    public HashExchange(String name) {
        super(name);
    }

    public HashExchange(String name, boolean durable, boolean autoDelete) {
        super(name, durable, autoDelete);
    }

    public HashExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
        super(name, durable, autoDelete, arguments);
    }

    public final String getType() {
        return "x-consistent-hash";
    }

}