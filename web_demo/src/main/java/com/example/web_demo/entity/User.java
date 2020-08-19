package com.example.web_demo.entity;

import java.util.List;

/**
 * 功能描述: User
 *
 * @author zhangaomin
 * @time 2020/8/19 14:13
 **/
public class User {

    private Long id;

    private String name;

    private String password;

    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
