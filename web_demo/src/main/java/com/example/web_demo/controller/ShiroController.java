package com.example.web_demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

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
public class ShiroController {

    @GetMapping(value = "/login")
    public String login(@RequestParam("name") String name) {
        System.out.println(SecurityUtils.getSubject().getSession().getId() + " " + SecurityUtils.getSubject().getSession().getStartTimestamp().toString());
        //添加用户认证信息
        UsernamePasswordToken usernamePasswordToken = null;
        if ("admin".equals(name)) {
            usernamePasswordToken = new UsernamePasswordToken("admin", "123456");
        } else {
            usernamePasswordToken = new UsernamePasswordToken("admin", "111111");
        }
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            SecurityUtils.getSubject().login(usernamePasswordToken);
            SecurityUtils.getSubject().getSession().setTimeout(30000);
        } catch (Exception e) {
            return "login error!";
        }
        return "login success";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        return "logout!";
    }

    @GetMapping(value = "/error")
    public String error() {
        return "无权限!";
    }

    @RequiresRoles("admin")
    @RequiresPermissions("add")
    @GetMapping(value = "/t1")
    public String t1() {
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

    /**
     * 无权限跳转
     *
     * @param request
     * @param response
     * @return
     */
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