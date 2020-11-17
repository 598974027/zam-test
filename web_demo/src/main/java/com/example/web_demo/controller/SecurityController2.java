//package com.example.web_demo.controller;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/security/user")
//public class SecurityController2 {
//
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public String hello() {
//        String currentUser = "";
//        Object principl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principl instanceof UserDetails) {
//            currentUser = ((UserDetails) principl).getUsername();
//        } else {
//            currentUser = principl.toString();
//        }
//        return currentUser + ":" + System.currentTimeMillis();
//    }
//
//}
