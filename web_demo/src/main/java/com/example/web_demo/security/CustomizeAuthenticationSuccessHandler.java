//package com.example.web_demo.security;
//
//import com.alibaba.fastjson.JSON;
//import com.example.web_demo.jwt.JwtUtil;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 功能描述: 登录成功
// *
// * @author zhangaomin
// * @time
// **/
//@Component
//public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
//        /**
//         * 登录成功
//         * 1.返回token
//         * 2.返回菜单树（包括功能按钮等）
//         */
//        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
//        httpServletResponse.setContentType("text/json;charset=utf-8");
//        httpServletResponse.setHeader("access_token", JwtUtil.generateToken(username));
//        httpServletResponse.getWriter().write(JSON.toJSONString("登录成功"));
//    }
//
//}