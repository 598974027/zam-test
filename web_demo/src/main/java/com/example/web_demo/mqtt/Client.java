package com.example.web_demo.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * 功能描述: Client
 *
 * @author zhangaomin
 * @time 2020/6/30 14:51
 **/
public class Client {
    public static final String HOST = "tcp://127.0.0.1:61613";
    private String userName = "admin";
    private String passWord = "password";

    private static final String CLIENT_ID = "client001";
    private static final String TOPIC = "top123";
    private static final String TOPIC2 = "top456";

    public MqttClient client;

    private void start() throws MqttException {
        //MemoryPersistence设置client-id的保存形式，默认为以内存保存
        client = new MqttClient(HOST, CLIENT_ID, new MemoryPersistence());
        //MQTT的连接设置
        MqttConnectOptions options = new MqttConnectOptions();
        //设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
        //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法
        options.setWill(client.getTopic(TOPIC2), "close".getBytes(), 1, false);
        client.connect(options);
        client.subscribe(TOPIC);
        client.setCallback(new MqttCallback() {

            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("---------connectionLost---------");
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
        });
        //发送消息
        MqttMessage message = new MqttMessage();
        message.setQos(1);
        message.setRetained(false);
        message.setPayload("给客户端topic456".getBytes());
        MqttDeliveryToken token = client.getTopic(TOPIC2).publish(message);
        token.waitForCompletion();
    }

    private void stop() throws MqttException {
        client.disconnect();
        client.close();
    }

    public static void main(String[] args) throws MqttException {
        Client client = new Client();
        client.start();
    }
}
