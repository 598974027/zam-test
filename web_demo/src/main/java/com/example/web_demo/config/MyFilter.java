package com.example.web_demo.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/26 13:42
 * @see
 **/
//@Component
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器逻辑操作。。。");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
