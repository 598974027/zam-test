//package com.example.web_demo.security;
//
//import com.example.web_demo.jwt.JwtUtil;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 功能描述: 登出成功处理逻辑
// *
// * @author zhangaomin
// * @time 2020/9/17 10:10
// **/
//@Component
//public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {
//
//    @Override
//    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        String tokenHeader = httpServletRequest.getHeader(JwtUtil.TOKEN_HEADER);
//        if (tokenHeader != null && tokenHeader.startsWith(JwtUtil.TOKEN_PREFIX)) {
//            System.out.println(tokenHeader.replace(JwtUtil.TOKEN_PREFIX, ""));
//        }
//        if (authentication != null) {
//            httpServletResponse.setContentType("text/json;charset=utf-8");
//            httpServletResponse.getWriter().write(authentication.getName() + "注销成功！");
//        } else {
//            httpServletResponse.setContentType("text/json;charset=utf-8");
//            httpServletResponse.getWriter().write("您已注销成功！");
//        }
//    }
//
//}
