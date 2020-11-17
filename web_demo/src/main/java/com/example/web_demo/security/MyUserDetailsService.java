//package com.example.web_demo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 功能描述: MyUserDetailsService
// *
// * @author zhangaomin
// * @time 2020/9/16 15:53
// **/
//@Component
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (username.equals("admin") || username.equals("user")) {
//            SecurityUser securityUser = new SecurityUser();
//            securityUser.setUsername(username);
//            //模拟从数据库中获取原密码
//            securityUser.setPassword(passwordEncoder.encode(username));
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            //模拟从数据库中获取角色
//            if (username.equals("admin")) {
//                authorities.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
//                authorities.add(new SimpleGrantedAuthority("ROLE_" + "USER"));
//            } else if (username.equals("user")) {
//                authorities.add(new SimpleGrantedAuthority("ROLE_" + "USER"));
//            }
//            securityUser.setAuthorities(authorities);
//            return securityUser;
//        } else {
//            throw new UsernameNotFoundException("用户(" + username + ")不存在");
//        }
//    }
//
//}
