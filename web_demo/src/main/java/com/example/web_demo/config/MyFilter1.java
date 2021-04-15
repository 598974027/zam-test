package com.example.web_demo.config;

import javax.servlet.*;
import java.io.IOException;

/**
 * 功能描述: 过滤器
 *
 * @author zhangam
 * @time 2019/5/26 13:42
 * @see
 **/
public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器1。。。 Before");
        if (servletRequest.getParameterMap().containsKey("zam")) {
            servletResponse.setContentType("application/json;charset=utf-8");
            servletResponse.getWriter().write("zam");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        System.out.println("过滤器1。。。After");
    }

}
