package com.example.web_demo.controller;

import com.example.web_demo.CountingUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zam")
public class Controller {

    @RequestMapping(value = "/tt", method = RequestMethod.GET)
    public String tt() {
        return "test";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() throws InterruptedException {
        CountingUtil.getInstance().updateCount("send", 1);
        Thread.sleep(1000);
        return "test";
    }

}
