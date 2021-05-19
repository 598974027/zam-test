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
//@Component
//@WebFilter(filterName = "myFilter2", urlPatterns = "/zam/*")
public class MyFilter2 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器2。。。 Before");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("过滤器2。。。After");
    }

}
