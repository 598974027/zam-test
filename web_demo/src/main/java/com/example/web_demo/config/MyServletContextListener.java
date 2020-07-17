package com.example.web_demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.ws.Action;

/**
 * 功能描述: 监听ServletContext对象
 *
 * @author zhangaomin
 * @time 2020/7/16 8:49
 **/
@Component
public class MyServletContextListener implements ServletContextListener {

    @Autowired
    public ServletContext servletContext;

    @Autowired
    public ApplicationContext applicationContext;

    @Autowired
    public WebApplicationContext webApplicationContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("监听ServletContext对象初始化操作。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("监听ServletContext对象结束操作。。。");
    }

}
