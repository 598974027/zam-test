package com.example.web_demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/aop2")
public class Controller2 {

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1(HttpServletRequest request) {
        return "test1";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    public String test2(HttpServletResponse response) {
        return "test2";
    }

}
