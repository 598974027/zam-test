//package com.example.web_demo.security;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
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
//         * 2.赋予权限
//         */
//        SysUser sysUser = new SysUser();
//        sysUser.setUsername(username);
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if ("admin".equals(username)) {
//            sysUser.setPassword(passwordEncoder.encode("admin"));
//            authorities.add(new SimpleGrantedAuthority("insert"));
//            authorities.add(new SimpleGrantedAuthority("select"));
//        } else if ("user".equals(username)) {
//            sysUser.setPassword(passwordEncoder.encode("user"));
//            authorities.add(new SimpleGrantedAuthority("select"));
//        }
//        sysUser.setAuthorities(authorities);
//        return sysUser;
//    }
//
//}
