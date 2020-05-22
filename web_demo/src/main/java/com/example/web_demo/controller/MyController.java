package com.example.web_demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.web_demo.cache.CacheTest;
import com.example.web_demo.jwt.TokenService;
import com.example.web_demo.jwt.User;
import com.example.web_demo.jwt.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MyController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    @Qualifier("cacheTest")
    private CacheTest cacheTest;

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1() {
        cacheTest.getUser(2);
        return "test1";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    public String test2() {
        cacheTest.getUser(2);
        return "test2";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(User user) {
        JSONObject jsonObject = new JSONObject();
        User userForBase = new User("1", "admin", "123456");
        if (user != null) {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                jsonObject.put("message", "密码错误");
                return jsonObject;
            } else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        } else {
            jsonObject.put("message", "登录失败");
            return jsonObject;
        }
    }

    //    @PassToken
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }

}
