//package com.example.web_demo.jwt;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * 功能描述: RbacAuthorityService
// *
// * @author zhangaomin
// * @time 2020/9/17 10:58
// **/
//@Component("rbacauthorityservice")
//public class RBACAuthorityService {
//
//    /**
//     * 对请求请求过滤（可选）
//     *
//     * @param request
//     * @param authentication
//     * @return
//     */
//    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
//        Object userInfo = authentication.getPrincipal();
//        boolean hasPermission = false;
//        if (userInfo instanceof UserDetails) {
//            String username = ((UserDetails) userInfo).getUsername();
//            Set<String> urls = new HashSet();
//            AntPathMatcher antPathMatcher = new AntPathMatcher();
//            for (String url : urls) {
//                if (antPathMatcher.match(url, request.getRequestURI())) {
//                    hasPermission = true;
//                    break;
//                }
//            }
//            hasPermission = true;
//        }
//        return hasPermission;
//    }
//
//}
//
