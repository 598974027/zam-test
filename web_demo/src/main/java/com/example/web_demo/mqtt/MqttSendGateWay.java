package com.example.web_demo.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * 功能描述: MqttSendGateWay
 *
 * @author zhangaomin
 * @time 2020/6/30 16:23
 **/
@Component
@MessagingGateway(defaultRequestChannel = "mqttOutputChannel")
public interface MqttSendGateWay {

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);

}

