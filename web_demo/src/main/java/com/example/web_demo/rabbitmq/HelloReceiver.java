package com.example.web_demo.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/29 9:10
 * @see
 **/
@Component
public class HelloReceiver {

//    @Autowired
//    private AmqpTemplate rabbitTemplate;

//    @RabbitHandler
//    @RabbitListener(queues = "zamzamzam")
//    public void process(Message message, Channel channel) {
//        try {
//            String str = new String(message.getBody());
//            System.out.println("Receiver  : " + str);
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            try {
//                channel.basicRecover();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
//        Message message2 = new Message("hello".getBytes(), new MessageProperties());
//        this.rabbitTemplate.send("exchange-zam", "", message2);
//    }

}
