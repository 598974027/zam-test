package com.example.web_demo.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/29 9:10
 * @see
 **/
@Component
public class HelloSend {

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

//    public void sendObject(Object msg) {
//        rabbitTemplate.setMandatory(true);
//        //确认模式
//        rabbitTemplate.setConfirmCallback(confirmCallback);
//        //未投递到退回模式
//        rabbitTemplate.setReturnCallback(returnCallback);
//        rabbitTemplate.convertAndSend("zam_exchange", "110", msg);
//    }

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("confirmCallback");
            if (!ack) {
                System.out.println();
            }
        }
    };

    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            System.out.println(MessageFormat.format("消息发送ReturnCallback:{0},{1},{2},{3},{4}", message, replyCode, replyText, exchange, routingKey));
        }
    };

}
