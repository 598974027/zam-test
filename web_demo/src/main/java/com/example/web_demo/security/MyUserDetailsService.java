package com.example.web_demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述: MyUserDetailsService
 *
 * @author zhangaomin
 * @time 2020/9/16 15:53
 **/
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("admin") || username.equals("user")) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (username.equals("admin")) {
                /**
                 * ROLE_注意前缀 ROLE_ADMIN 对应 ADMIN
                 */
                authorities.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
                authorities.add(new SimpleGrantedAuthority("ROLE_" + "USER"));
            } else {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + "USER"));
            }
            SecurityUser securityUser = new SecurityUser(authorities);
            securityUser.setUsername(username);
            securityUser.setPassword(passwordEncoder.encode(username));
            return securityUser;
        } else {
            throw new UsernameNotFoundException("用户(" + username + ")不存在");
        }
    }

}
