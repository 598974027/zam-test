//package com.example.web_demo.security;
//
//import com.alibaba.fastjson.JSON;
//import org.springframework.security.authentication.*;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 功能描述: 登录失败
// *
// * @author zhangaomin
// * @time
// **/
//@Component
//public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
//        String result = null;
//        if (e instanceof AccountExpiredException) {
//            //账号过期
//            result = "账号过期";
//        } else if (e instanceof BadCredentialsException) {
//            //密码错误
//            result = "密码错误";
//        } else if (e instanceof CredentialsExpiredException) {
//            //密码过期
//            result = "密码过期";
//        } else if (e instanceof DisabledException) {
//            //账号不可用
//            result = "账号不可用";
//        } else if (e instanceof InternalAuthenticationServiceException) {
//            //拦截处理时的异常
//            result = "拦截处理时的异常";
//        } else if (e instanceof LockedException) {
//            //账号锁定
//            result = "账号锁定";
//        } else {
//            //其他错误
//            result = "其他错误";
//        }
//        httpServletResponse.setContentType("text/json;charset=utf-8");
//        httpServletResponse.getWriter().write(JSON.toJSONString("登录失败:" + result + "(" + e.getMessage() + ")"));
//    }
//
//}
