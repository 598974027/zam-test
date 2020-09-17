package com.example.web_demo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.*;

import java.util.Date;

/**
 * 功能描述: JwtUtil
 *
 * @author zhangaomin
 * @time 2020/9/17 11:04
 **/
public class JwtUtil {

    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String APP_SECRET_KEY = "zam_secret";

    public static final long TIME = 1000 * 60 * 60;

    public static String getToken(User user) {
        return JWT.create().withAudience(user.getId()).sign(Algorithm.HMAC256(user.getPassword()));
    }

    public static String createToken(String username) {
        return Jwts
                .builder()
                .setId(username) //ID
                .setSubject(username) //主题
                .setIssuedAt(new Date()) //签发时间
                .setIssuer("zam") //签发者
                .claim("username", username) //自定义属性
                .setExpiration(new Date(System.currentTimeMillis() + TIME)) //失效时间
                .signWith(SignatureAlgorithm.HS256, APP_SECRET_KEY) //签名算法和密钥
                .compact();
    }

    public static String getUsername(String token) {
        try {
            Claims claims = Jwts
                    .parser()
                    .setSigningKey(APP_SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("username").toString();
        } catch (ExpiredJwtException e) {
            System.out.println("*****token过期*****");
            return null;
        } catch (SignatureException e) {
            System.out.println("*****token不正确*****");
            return null;
        } catch (MalformedJwtException e) {
            System.out.println("*****token格式不对*****");
            return null;
        }
    }

}
