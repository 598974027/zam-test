package com.example.web_demo.security;

import com.alibaba.fastjson.JSON;
import com.example.web_demo.controller.AjaxResponseBody;
import com.example.web_demo.jwt.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述: 登录成功处理逻辑
 *
 * @author zhangaomin
 * @time 2020/9/17 10:07
 **/
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();
        responseBody.setStatus("200");
        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        responseBody.setMsg(userDetails.getUsername() + "登录成功");
        responseBody.setJwtToken(JwtUtil.createToken(userDetails.getUsername()));
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }

}