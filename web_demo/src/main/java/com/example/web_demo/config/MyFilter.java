package com.example.web_demo.config;

import org.apache.catalina.connector.RequestFacade;

import javax.servlet.*;
import java.io.IOException;

/**
 * 功能描述: 过滤器
 *
 * @author zhangam
 * @time 2019/5/26 13:42
 * @see
 **/
//@Component
//@WebFilter(filterName = "myFilter", urlPatterns = "/test/*")
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            System.out.println("过滤器逻辑操作。。。" + ((RequestFacade) servletRequest).getRequestURL().toString());
        } catch (Exception e) {
            System.out.println("过滤器逻辑操作。。。");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
