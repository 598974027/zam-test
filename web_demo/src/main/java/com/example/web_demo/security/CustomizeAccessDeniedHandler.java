//package com.example.web_demo.security;
//
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 功能描述: 用来解决认证过的用户访问无权限资源时的异常
// *
// * @author zhangaomin
// * @time 2020/9/17 10:30
// **/
//@Component
//public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {
//
//    @Override
//    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        httpServletResponse.setContentType("text/json;charset=utf-8");
//        httpServletResponse.getWriter().write("拒绝访问！");
//    }
//
//}