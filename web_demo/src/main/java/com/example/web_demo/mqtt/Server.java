package com.example.web_demo.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * 功能描述: 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
 *
 * @author zhangaomin
 * @time 2020/6/30 14:49
 **/
public class Server {

    public static final String HOST = "tcp://127.0.0.1:61613";
    private String userName = "admin";
    private String passWord = "password";
    private static final String CLIENTID = "server001";
    public static final String TOPIC = "toclient124";
    public static final String TOPIC125 = "toclient125";
    public MqttClient client;
    public MqttTopic topic;
    public MqttTopic topic125;
    public MqttMessage message;

    public void connect() throws MqttException {
        client = new MqttClient(HOST, CLIENTID, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        //设置超时时间
        options.setConnectionTimeout(10);
        //设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallback());
            client.connect(options);
            topic = client.getTopic(TOPIC);
            topic125 = client.getTopic(TOPIC125);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() throws MqttException {
        client.disconnect();
        client.close();
    }

    public void publish(MqttTopic topic, MqttMessage message) throws MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! " + token.isComplete());
    }

    public static void main(String[] args) throws MqttException {
        Server server = new Server();
        server.connect();
        server.message = new MqttMessage();
        server.message.setQos(2);
        server.message.setRetained(true);
        server.message.setPayload("给客户端124推送的信息".getBytes());
        server.publish(server.topic, server.message);
        System.out.println(server.message.isRetained() + "------ratained状态");
        server.message = new MqttMessage();
        server.message.setQos(2);
        server.message.setRetained(true);
        server.message.setPayload("给客户端125推送的信息".getBytes());
        server.publish(server.topic125, server.message);
        System.out.println(server.message.isRetained() + "------ratained状态");
        server.close();
    }

}
