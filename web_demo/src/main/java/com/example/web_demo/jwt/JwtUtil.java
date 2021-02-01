package com.example.web_demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: JwtUtil
 *
 * @author zhangaomin
 * @time 2020/9/17 11:04
 **/
public class JwtUtil {
    public static final String APP_SECRET_KEY = "zam_secret";

    /**
     * 初始化生成token的参数
     *
     * @param username
     * @return String
     */
    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>(1);
        claims.put("username", username);
        return generateToken(claims);
    }

    /**
     * 生成token
     *
     * @param claims
     * @return String
     */
    public static String generateToken(Map<String, Object> claims) {
        LocalDateTime now = LocalDateTime.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject("watch") //主题
                .setIssuer("notion") //签发者
                .setIssuedAt(new Date(now.toInstant(ZoneOffset.of("+8")).toEpochMilli())) //签发时间
                .setExpiration(new Date(now.plusMinutes(10).toInstant(ZoneOffset.of("+8")).toEpochMilli())) //失效时间
                .signWith(SignatureAlgorithm.HS256, APP_SECRET_KEY) //签名算法和密钥
                .compact();
    }

    /**
     * 验证token
     *
     * @param token
     * @return String
     */
    public static String verifyToken(String token) {
        String result = null;
        try {
            Claims claims = Jwts.parser().setSigningKey(APP_SECRET_KEY).parseClaimsJws(token).getBody();
            result = claims.get("username").toString();
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 判断token能否刷新
     *
     * @param token
     * @return Boolean
     */
    public static Boolean canTokenRefreshed(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(APP_SECRET_KEY).parseClaimsJws(token).getBody();
            final Date exp = claims.getExpiration();
            LocalDateTime time = exp.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
            if (LocalDateTime.now().plusMinutes(3).isAfter(time)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 刷新token
     *
     * @param token
     * @return String
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = Jwts.parser().setSigningKey(APP_SECRET_KEY).parseClaimsJws(token).getBody();
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

}
