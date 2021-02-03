//package com.example.web_demo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 功能描述: spring security配置
// *
// * @author zhangaomin
// * @time
// * @see
// **/
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//
//    @Autowired
//    private CustomizeAccessDeniedHandler customizeAccessDeniedHandler;
//
//    @Autowired
//    private CustomizeAuthenticationEntryPoint customizeAuthenticationEntryPoint;
//
//    @Autowired
//    private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
//
//    @Autowired
//    private CustomizeAuthenticationFailureHandler customizeAuthenticationFailureHandler;
//
//    @Autowired
//    private CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;
//
//    @Autowired
//    private MyAuthenticationTokenFilter myAuthenticationTokenFilter;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * 忽略拦截
//     *
//     * @param web
//     * @throws Exception
//     */
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/webjars/**", "/v2/**", "/swagger-resources/**", "/swagger-ui.html");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        /**
//         * 数据库用户信息匹配
//         */
//        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
//        /**
//         * 内存用户信息匹配
//         */
////        auth.inMemoryAuthentication()
////                .withUser("admin").password(passwordEncoder().encode("admin"))
////                .and()
////                .withUser("user").password(passwordEncoder().encode("user"));
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        /**
//         * 数据库添加接口权限
//         */
//        List permissions = new ArrayList();
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http.authorizeRequests();
//        permissions.forEach(p -> {
//            authorizeRequests.antMatchers("").hasAnyAuthority("");
//        });
//
//        authorizeRequests.antMatchers("/security/insert").hasAnyAuthority("insert");
//        authorizeRequests.antMatchers("/security/delete").hasAnyAuthority("delete");
//        authorizeRequests.antMatchers("/security/update").hasAnyAuthority("update");
//        authorizeRequests.antMatchers("/security/select", "/security/selectAll").hasAnyAuthority("select");
//
//        authorizeRequests
//                /**
//                 * 所以请求都要认证
//                 */
//                .anyRequest().authenticated()
//                /**
//                 * 基于token验证，去掉session
//                 */
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/j_spring_security_check")
//                .successHandler(customizeAuthenticationSuccessHandler)
//                .failureHandler(customizeAuthenticationFailureHandler)
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/j_spring_security_logout")
//                .logoutSuccessHandler(customizeLogoutSuccessHandler)
//                .deleteCookies("JSESSIONID")
//                .permitAll()
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(customizeAccessDeniedHandler)
//                .authenticationEntryPoint(customizeAuthenticationEntryPoint);
//        /**
//         * 关闭csrf
//         */
//        http.csrf().disable().cors();
//        /**
//         * 基于token验证
//         */
//        http.addFilterBefore(myAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}
