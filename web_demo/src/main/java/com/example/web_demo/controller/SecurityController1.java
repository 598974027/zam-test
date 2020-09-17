package com.example.web_demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security/admin")
public class SecurityController1 {

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        String currentUser = "";
        Object principl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principl instanceof UserDetails) {
            currentUser = ((UserDetails) principl).getUsername();
        } else {
            currentUser = principl.toString();
        }
        return currentUser + ":" + System.currentTimeMillis();
    }

    @RequestMapping(value = "/me1", method = RequestMethod.GET)
    public Object me1(Authentication authentication) {
        return authentication;
    }

    @RequestMapping(value = "/me2", method = RequestMethod.GET)
    public Object me2(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

}
