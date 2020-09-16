//package com.example.web_demo.mqtt;
//
//import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.annotation.IntegrationComponentScan;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.core.MessageProducer;
//import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
//import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
//import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
//import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
//import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.MessageHandler;
//import org.springframework.messaging.MessagingException;
//
///**
// * 功能描述: MqttReceiveConfig
// *
// * @author zhangaomin
// * @time 2020/6/30 16:05
// **/
//@Configuration
//@IntegrationComponentScan
//public class MqttReceiveConfig {
//
//    @Value("${spring.mqtt.url}")
//    private String hostUrl;
//
//    @Value("${spring.mqtt.username}")
//    private String username;
//
//    @Value("${spring.mqtt.password}")
//    private String password;
//
//    @Value("${spring.mqtt.client.id}")
//    private String clientId;
//
//    @Value("${spring.mqtt.topic}")
//    private String topic;
//
//    @Value("${spring.mqtt.timeout}")
//    private int timeout;
//
//    @Bean
//    public MqttConnectOptions getMqttConnectOptions() {
//        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
//        mqttConnectOptions.setUserName(username);
//        mqttConnectOptions.setPassword(password.toCharArray());
//        mqttConnectOptions.setServerURIs(new String[]{hostUrl});
//        mqttConnectOptions.setKeepAliveInterval(2);
//        return mqttConnectOptions;
//    }
//
//    @Bean
//    public MqttPahoClientFactory mqttClientFactory() {
//        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
//        factory.setConnectionOptions(getMqttConnectOptions());
//        return factory;
//    }
//
//    //接收通道
//    @Bean
//    public MessageChannel mqttInputChannel() {
//        return new DirectChannel();
//    }
//
//    //接收配置
//    @Bean
//    public MessageProducer inbound() {
//        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
//                clientId, mqttClientFactory(), topic.split(","));
//        adapter.setCompletionTimeout(timeout);
//        adapter.setConverter(new DefaultPahoMessageConverter());
//        adapter.setQos(1);
//        adapter.setOutputChannel(mqttInputChannel());
//        return adapter;
//    }
//
//    //通过通道获取数据
//    @Bean
//    @ServiceActivator(inputChannel = "mqttInputChannel")
//    public MessageHandler handler() {
//        return new MessageHandler() {
//            @Override
//            public void handleMessage(Message<?> message) throws MessagingException {
//                String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
//                String type = topic.substring(topic.lastIndexOf("/") + 1, topic.length());
//                if ("hello".equalsIgnoreCase(topic)) {
//                    System.out.println("hello    " + message.getPayload().toString());
//                } else if ("hello1".equalsIgnoreCase(topic)) {
//                    System.out.println("hello1   " + message.getPayload().toString());
//                } else if ("hello2".equalsIgnoreCase(topic)) {
//                    System.out.println("hello2   " + message.getPayload().toString());
//                }
//            }
//        };
//    }
//
//    //发送通道
//    @Bean
//    public MessageChannel mqttOutputChannel() {
//        return new DirectChannel();
//    }
//
//    //发送配置
//    @Bean
//    @ServiceActivator(inputChannel = "mqttOutputChannel")
//    public MessageHandler mqttOutbound() {
//        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(MqttAsyncClient.generateClientId(), mqttClientFactory());
//        messageHandler.setAsync(true);
//        messageHandler.setDefaultQos(1);
//        messageHandler.setDefaultTopic("hello");
//        return messageHandler;
//    }
//
//}
