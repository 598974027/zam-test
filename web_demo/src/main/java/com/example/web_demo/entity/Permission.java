package com.example.web_demo.entity;

/**
 * 功能描述: Permission
 *
 * @author zhangaomin
 * @time 2020/8/19 14:14
 **/
public class Permission {

    private Long id;

    private String permission;

    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
