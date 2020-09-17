package com.example.web_demo.security;

import com.example.web_demo.jwt.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    @Autowired
    private CustomizeAccessDeniedHandler customizeAccessDeniedHandler;

    @Autowired
    private CustomizeAuthenticationEntryPoint customizeAuthenticationEntryPoint;

    @Autowired
    private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Autowired
    private CustomizeAuthenticationFailureHandler customizeAuthenticationFailureHandler;

    @Autowired
    private CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter; //JWT拦截器

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
                        "/configuration/ui");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 内存设置权限信息
         */
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

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors(); //安全器令牌
        http.authorizeRequests().antMatchers("/aop1/**", "/aop2/**").permitAll(); //忽略拦截
        http.authorizeRequests().antMatchers("/security/admin/me*").permitAll();
        http.authorizeRequests().anyRequest().authenticated(); //请求验证才能访问
        http.formLogin()
                .loginPage("/login.html")//登录页面url
                .loginProcessingUrl("/j_spring_security_check") //登录处理url
                .defaultSuccessUrl("/success.html") //登录成功跳转url
                .failureUrl("/failure.html") //登录失败跳转url
                .permitAll();
        http.logout()
                .logoutUrl("/j_spring_security_logout") //注销url
                .logoutSuccessUrl("/login.html") //注销成功跳转url
                .permitAll();
    }*/

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors();
        http.authorizeRequests().antMatchers("/aop1/**", "/aop2/**").permitAll();
        http.authorizeRequests().antMatchers("/security/admin/me*").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/j_spring_security_check")
                .successHandler(customizeAuthenticationSuccessHandler)
                .failureHandler(customizeAuthenticationFailureHandler)
                .permitAll();
        http.logout()
                .logoutUrl("/j_spring_security_logout")
                .logoutSuccessHandler(customizeLogoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .permitAll();
        http.exceptionHandling()
                .accessDeniedHandler(customizeAccessDeniedHandler) //权限拒绝处理逻辑
                .authenticationEntryPoint(customizeAuthenticationEntryPoint); //匿名用户访问无权限资源时的异常处理
        http.headers().frameOptions().disable(); //防止iframe造成跨域
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()
//                .access("@rbacauthorityservice.hasPermission(request, authentication)")
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .successHandler(customizeAuthenticationSuccessHandler)
                .failureHandler(customizeAuthenticationFailureHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/j_spring_security_logout")
                .logoutSuccessHandler(customizeLogoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customizeAccessDeniedHandler)
                .authenticationEntryPoint(customizeAuthenticationEntryPoint);
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
