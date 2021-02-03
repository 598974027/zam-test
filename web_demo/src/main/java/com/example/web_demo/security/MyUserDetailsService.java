//package com.example.web_demo.security;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
///**
// * 功能描述: 自定义UserDetailsService
// *
// * @author zhangaomin
// * @time
// **/
//@Component
//public class MyUserDetailsService implements UserDetailsService {
//    protected final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        /**
//         * 1.查用户
//         * 2.设置密码
//         */
//        SysUser sysUser = new SysUser();
//        sysUser.setUsername(username);
//        if ("admin".equals(username)) {
//            sysUser.setPassword(passwordEncoder.encode("admin"));
//        } else if ("user".equals(username)) {
//            sysUser.setPassword(passwordEncoder.encode("user"));
//        }
//        return sysUser;
//    }
//
//}