package com.intest.mybatis2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MyController {

    @Autowired
    private MyService myService;

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1() {
        System.out.println(myService.test());
        return "test1";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    public String test2() {
        return "test2";
    }

}
