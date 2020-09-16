package com.example.web_demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.web_demo.aspect.UseCase;
import com.example.web_demo.cache.CacheTest;
import com.example.web_demo.jwt.PassToken;
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
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    @Qualifier("cacheTest")
    private CacheTest cacheTest;

    @UseCase(id = "zam")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
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

    @PassToken
    @GetMapping("/getMessage1")
    public String getMessage1() {
        return "你已通过验证1";
    }

    @UserLoginToken
    @GetMapping("/getMessage2")
    public String getMessage2() {
        return "你已通过验证2";
    }

}
