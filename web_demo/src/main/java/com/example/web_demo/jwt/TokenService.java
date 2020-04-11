package com.example.web_demo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

/**
 * @author zhangam
 * @date 2018-07-08 21:04
 */
@Service("TokenService")
public class TokenService {

    public String getToken(User user) {
        String token = JWT.create().withAudience(user.getId()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

}