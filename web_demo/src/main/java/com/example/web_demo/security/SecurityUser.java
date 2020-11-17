//package com.example.web_demo.security;
//
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.io.Serializable;
//import java.util.Collection;
//
///**
// * 功能描述: SecurityUser
// *
// * @author zhangaomin
// * @time 2020/9/16 15:56
// **/
//@Data
//public class SecurityUser implements Serializable, UserDetails {
//
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * 权限
//     */
//    private Collection<? extends GrantedAuthority> authorities;
//
//    /**
//     * 用户id
//     */
//    private Integer id;
//
//    /**
//     * 用户名
//     */
//    private String username;
//
//    /**
//     * 用户密码
//     */
//    private String password;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//}