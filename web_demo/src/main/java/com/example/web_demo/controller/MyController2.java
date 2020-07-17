package com.example.web_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test2")
@Slf4j
public class MyController2 {

//    @Autowired
//    private HelloSend helloSend;

//    @Autowired
//    private MqttSendGateWay mqttSendGateWay;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send() {
//        helloSend.sendObject("123456");
        return "send";
    }

    @RequestMapping(value = "/send2", method = RequestMethod.GET)
    public String send2() {
//        mqttSendGateWay.sendToMqtt("hello2", 1, "post message");
        return "send";
    }

}
