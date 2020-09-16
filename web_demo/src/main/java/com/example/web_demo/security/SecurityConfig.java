package com.example.web_demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 功能描述: spring security配置
 *
 * @author zhangam
 * @time 2020/5/20 9:15
 * @see
 **/
@Configuration
@EnableWebSecurity
//开启方法级安全验证 @PreAuthorize @PostAuthorize @PreFilter @PostFilter
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 忽略拦截
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/swagger-ui.html",
                        "/swagger-resources/**",
                        "/webjars/springfox-swagger-ui/**",
                        "/images/**",
                        "/v2/api-docs/**",
                        "/configuration/security",
                        "/configuration/ui",
                        "/login.html",
                        "/success.html",
                        "/failure.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin") //管理员 ADMIN、USER角色
//                .password("{noop}admin")
//                .roles("ADMIN", "USER")
//                .and()
//                .withUser("user")
//                .password("{noop}user") //普通用户 USER角色
//                .roles("USER");

        /**
         * 从数据库读取的用户进行身份认证
         */
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers(
//                        "/aop1/**",
//                        "/aop2/**",
//                        "/security/admin/me*").permitAll() //忽略拦截
//                .antMatchers("/security/admin/**").hasRole("ADMIN")
//                .antMatchers("/security/user/**").hasRole("USER")
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login.html") //登录页面url
                .loginProcessingUrl("/j_spring_security_check") //登录处理url
                .defaultSuccessUrl("/success.html") //登录成功跳转url
                .failureUrl("/failure.html").permitAll() //登录失败跳转url
                .and().logout()
                .logoutUrl("/j_spring_security_logout") //注销url
                .logoutSuccessUrl("/login.html").permitAll() //注销成功跳转url
                .and().httpBasic()
                .and().csrf().disable();
    }

}
