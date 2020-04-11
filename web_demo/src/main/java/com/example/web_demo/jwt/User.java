package com.example.web_demo.jwt;

import lombok.Data;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/7/1 15:45
 * @see
 **/
@Data
public class User {

    private String id;
    private String username;
    private String password;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
