package com.example.web_demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * 功能描述: 监听客户端请求ServletRequest对象
 *
 * @author zhangaomin
 * @time 2020/7/16 8:53
 **/
@Component
public class MyServletRequestListener implements ServletRequestListener {

    private static final Logger logger = LoggerFactory.getLogger(MyServletRequestListener.class);

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
//        System.out.println("监听客户端请求初始化操作。。。");
//        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
//        request.setAttribute("id", request.getRequestedSessionId());
//        request.setAttribute("url", request.getRequestURL());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
//        System.out.println("监听客户端请求初始化结束操作。。。");
//        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
//        logger.info("session id为：{}", request.getAttribute("id"));
//        logger.info("request url为：{}", request.getAttribute("url"));
    }

}
