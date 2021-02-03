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

    /**
     * 设置安全管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        securityManager.setRealm(myShiroRealm);
        return securityManager;
    }

    /**
     * 设置对应的过滤条件和跳转条件
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         * 配置拦截器
         */
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/shiro/logout", "anon");
        filterMap.put("/shiro/**", "authc");
        //登录认证
        shiroFilterFactoryBean.setLoginUrl("/shiro/login");
        //登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/shiro/success");
        //未授权界面
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

