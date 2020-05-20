package com.example.web_demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String admin() {
        Object principl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUser = null;
        if (principl instanceof UserDetails) {
            currentUser = ((UserDetails) principl).getUsername();
        } else {
            currentUser = principl.toString();
        }
        return "能访问，用户：" + currentUser;
    }

    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public String user() {
        Object principl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUser = null;
        if (principl instanceof UserDetails) {
            currentUser = ((UserDetails) principl).getUsername();
        } else {
            currentUser = principl.toString();
        }
        return "能访问，用户：" + currentUser;
    }

}
