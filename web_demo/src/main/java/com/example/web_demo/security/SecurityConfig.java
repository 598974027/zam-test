package com.example.web_demo.security;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 功能描述: spring security配置
 *
 * @author zhangam
 * @time 2020/5/20 9:15
 * @see
 **/
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private AuthenticationSuccessHandler authenticationSuccessHandler;
//
//    @Autowired
//    private AuthenticationFailureHandler authenticationFailureHandler;
//
//    @Autowired
//    private AuthenticationEntryPoint authenticationEntryPoint;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/springfox-swagger-ui/**", "/images/**", "/v2/api-docs/**", "/test/**").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasRole("USER")
//                .anyRequest().authenticated()
//                .and().formLogin()//.loginProcessingUrl("/login").successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
//                //.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
//                .and().httpBasic();
//        //关闭CSRF跨域
//        http.csrf().disable();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin") //管理员
//                .password("{noop}admin")
//                .roles("ADMIN", "USER")
//                .and()
//                .withUser("user").password("{noop}user") //普通用户
//                .roles("USER");
//    }
//
//}
