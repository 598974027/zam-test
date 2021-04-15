package com.example.web_demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述: 过滤器
 *
 * @author zhangam
 * @time 2019/5/26 13:42
 * @see
 **/
@Component
@WebFilter(filterName = "myFilter3", urlPatterns = "/zam/*")
public class MyFilter3 extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("过滤器3。。。 Before");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        System.out.println("过滤器3。。。After");
    }

}
