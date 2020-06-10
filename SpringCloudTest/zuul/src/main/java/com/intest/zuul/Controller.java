package com.intest.zuul;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(value = "/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "hello " + name;
    }

}
