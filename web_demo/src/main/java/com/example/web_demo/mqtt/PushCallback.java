package com.example.web_demo.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 功能描述: PushCallback
 *
 * @author zhangaomin
 * @time 2020/6/30 15:14
 **/
public class PushCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("连接断开，可以做重连");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("---------deliveryComplete---------");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("接收消息主题---------" + topic);
        System.out.println("接收消息Qos---------" + message.getQos());
        System.out.println("接收消息内容---------" + new String(message.getPayload()));
    }

}