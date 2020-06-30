package com.example.web_demo.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.web_demo.aspect.UseCase;
import com.example.web_demo.cache.CacheTest;
import com.example.web_demo.jwt.TokenService;
import com.example.web_demo.jwt.User;
import com.example.web_demo.jwt.UserLoginToken;
import com.example.web_demo.mqtt.MqttSendGateWay;
import com.example.web_demo.rabbitmq.HelloSend;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/test2")
@Slf4j
public class MyController2 {

    @Autowired
    private HelloSend helloSend;

    @Autowired
    private MqttSendGateWay mqttSendGateWay;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send() {
//        helloSend.sendObject("123456");
        return "send";
    }

    @RequestMapping(value = "/send2", method = RequestMethod.GET)
    public String send2() {
        mqttSendGateWay.sendToMqtt("hello2", 1, "post message");
        return "send";
    }

}
