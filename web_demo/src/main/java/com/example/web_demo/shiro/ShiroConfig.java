package com.example.web_demo.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * 功能描述: ShiroConfig
 *
 * @author zhangaomin
 * @time 2020/8/19 13:59
 **/
@Configuration
public class ShiroConfig {

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        securityManager.setRealm(myShiroRealm);
        return securityManager;
    }

    //设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterMap = new HashMap<>();
        //swagger
        filterMap.put("/swagger**/**", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/v2/**", "anon");
        //登出
        filterMap.put("/shiro/logout", "logout");
        //对shiro开头请求认证
        //主要这行代码必须放在所有权限设置的最后
        filterMap.put("/shiro/**", "authc");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/shiro/login");
        //执行成功
//        shiroFilterFactoryBean.setSuccessUrl("/shiro/success");
        //发生错误
//        shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}

