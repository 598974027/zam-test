//package com.example.web_demo.security;
//
//import com.example.web_demo.jwt.JwtUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 功能描述: JWTAuthorizationFilter
// *
// * @author zhangaomin
// * @time
// **/
//@Component
//public class MyAuthenticationTokenFilter extends OncePerRequestFilter {
//    protected final Logger logger = LoggerFactory.getLogger(MyAuthenticationTokenFilter.class);
//
//    private final String tokenHeader = "Authorization";
//
//    private final String tokenHead = "Bearer ";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        /**
//         * 1.token验证、续约操作
//         * 2.查用户
//         * 3.赋予接口权限
//         */
//        String authHeader = request.getHeader(tokenHeader);
//        if (authHeader != null && authHeader.startsWith(tokenHead)) {
//            String token = authHeader.replace(tokenHead, "");
//            String username = JwtUtil.verifyToken(token);
//            if (username != null) {
//                /**
//                 * 查用户 赋予接口权限
//                 */
//                SysUser sysUser = new SysUser();
//                sysUser.setUsername(username);
//                List<GrantedAuthority> authorities = new ArrayList<>();
//                if ("admin".equals(username)) {
//                    authorities.add(new SimpleGrantedAuthority("insert"));
//                    authorities.add(new SimpleGrantedAuthority("select"));
//                } else if ("user".equals(username)) {
//                    authorities.add(new SimpleGrantedAuthority("select"));
//                }
//                sysUser.setAuthorities(authorities);
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(sysUser, null, sysUser.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                /**
//                 * 判断是否刷新token
//                 */
//                if (JwtUtil.canTokenRefreshed(token)) {
//                    logger.info("token刷新了");
//                    response.setHeader("access_token", JwtUtil.refreshToken(token));
//                } else {
//                    response.setHeader("access_token", token);
//                }
//            } else {
//                logger.error("token失效");
//            }
//        }
//        chain.doFilter(request, response);
//    }
//}