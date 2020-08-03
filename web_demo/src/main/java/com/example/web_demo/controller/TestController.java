package com.example.web_demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("message", "this is index jsp page");
        return "index";
    }

}
