package com.example.web_demo.entity;

import java.util.List;

/**
 * 功能描述: Role
 *
 * @author zhangaomin
 * @time 2020/8/19 14:13
 **/
public class Role {

    private Long id;

    private String roleName;

    private User user;

    private List<Permission> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

}
