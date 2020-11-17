//package com.example.web_demo.security;
//
//import com.alibaba.fastjson.JSON;
//import com.example.web_demo.controller.AjaxResponseBody;
//import org.springframework.security.authentication.*;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 功能描述: 登录失败处理逻辑
// *
// * @author zhangaomin
// * @time 2020/9/17 10:04
// **/
//@Component
//public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        AjaxResponseBody responseBody = new AjaxResponseBody();
//        responseBody.setStatus("400");
//        String result = null;
//        if (e instanceof AccountExpiredException) {
//            //账号过期
//            result = "账号过期！";
//        } else if (e instanceof BadCredentialsException) {
//            //密码错误
//            result = "账号密码错误！";
//        } else if (e instanceof CredentialsExpiredException) {
//            //密码过期
//            result = "密码过期！";
//        } else if (e instanceof DisabledException) {
//            //账号不可用
//            result = "账号不可用！";
//        } else if (e instanceof LockedException) {
//            //账号锁定
//            result = "账号锁定！";
//        } else if (e instanceof InternalAuthenticationServiceException) {
//            //用户不存在
//            result = "用户不存在！";
//        } else {
//            //其他错误
//            result = "其他错误！";
//        }
//        responseBody.setMsg("登录失败：" + result);
//        httpServletResponse.setContentType("text/json;charset=utf-8");
//        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
//    }
//
//}
