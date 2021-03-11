package com.example.web_demo.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 功能描述: 监听HTTP会话Session对象
 *
 * @author zhangaomin
 * @time 2020/7/16 8:51
 **/
public class MyHttpSessionListener implements HttpSessionListener {

    public static int online = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
//        System.out.println("监听HTTP会话Session对象+1");
        online++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
//        System.out.println("监听HTTP会话Session对象-1");
        online--;
    }

}