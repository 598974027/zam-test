package com.example.web_demo.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * 功能描述: Client
 *
 * @author zhangaomin
 * @time 2020/6/30 14:51
 **/
public class Client {

    public static final String HOST = "tcp://10.27.100.230:1883";
    private String userName = "username";
    private String passWord = "password";
    private static final String CLIENTID = "client001";
    private static final String TOPIC = "toclient124";
    public MqttClient client;

    private void start() {
        try {
            //MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(HOST, CLIENTID, new MemoryPersistence());
            //MQTT的连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            //设置是否清空session，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            options.setUserName(userName);
            options.setPassword(passWord.toCharArray());
            //设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            //设置会话心跳时间 单位为秒
            //服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            //设置回调
            client.setCallback(new PushCallback());
            MqttTopic topic = client.getTopic(TOPIC);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法，设置最终端口的通知消息
            options.setWill(topic, "close".getBytes(), 2, true);
            client.connect(options);
            //订阅消息
            client.subscribe(new String[]{TOPIC}, new int[]{1});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MqttException {
        Client client = new Client();
        client.start();
    }

}
