package com.example.web_demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/aop1")
public class Controller1 {

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1(String id) {
        return "test1";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    public String test2(HttpServletResponse response) {
        int a = 1 / 0;
        return "test2";
    }

}
