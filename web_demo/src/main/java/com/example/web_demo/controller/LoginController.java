package com.example.web_demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述: LoginController
 *
 * @author zhangaomin
 * @time 2020/8/19 14:12
 **/
@RestController
@RequestMapping("/shiro")
public class LoginController {

    @GetMapping(value = "/login")
    public String login() {
        System.out.println(SecurityUtils.getSubject().getSession().getId() + " " + SecurityUtils.getSubject().getSession().getStartTimestamp().toString());
        System.out.println(SecurityUtils.getSubject().getSession().getId() + " " + SecurityUtils.getSubject().getSession().getLastAccessTime().toString());
        //添加用户认证信息
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "123456");
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            SecurityUtils.getSubject().login(usernamePasswordToken);
            //设置30s的超时时间,好像不起作用
            SecurityUtils.getSubject().getSession().setTimeout(30000);
        } catch (Exception e) {
            return "post login error!";
        }
        return "post login ok!";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        return "xxxxx";
    }

    @GetMapping(value = "/success")
    public String success() {
        return "index page!";
    }

    @GetMapping(value = "/error")
    public String error() {
        return "操作发生错误!";
    }

    @RequiresRoles("admin")
    @RequiresPermissions("add")
    @GetMapping(value = "/t1")
    public String t1() {
        System.out.println(SecurityUtils.getSubject().getSession().getId() + " " + SecurityUtils.getSubject().getSession().getStartTimestamp().toString());
        System.out.println(SecurityUtils.getSubject().getSession().getId() + " " + SecurityUtils.getSubject().getSession().getLastAccessTime().toString());
        return "t1 success!";
    }

    @RequiresRoles("admin")
    @RequiresPermissions("delete")
    @GetMapping(value = "/t2")
    public String t2() {
        return "t2 success!";
    }

    @RequiresRoles("user")
    @RequiresPermissions("add")
    @GetMapping(value = "/t3")
    public String t3() {
        return "t3 success!";
    }

    @RequiresRoles("user")
    @RequiresPermissions("delete")
    @GetMapping(value = "/t4")
    public String t4() {
        return "t4 success!";
    }

    @ExceptionHandler({AuthorizationException.class})
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("/shiro/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ExceptionHandler({UnauthorizedException.class})
    public String unauthorizedException(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("/shiro/error");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}