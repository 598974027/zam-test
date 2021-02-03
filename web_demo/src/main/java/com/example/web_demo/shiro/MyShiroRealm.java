package com.example.web_demo.shiro;

import com.example.web_demo.entity.Permission;
import com.example.web_demo.entity.Role;
import com.example.web_demo.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述: MyShiroRealm
 *
 * @author zhangaomin
 * @time 2020/8/19 13:57
 **/
public class MyShiroRealm extends AuthorizingRealm {

    /**
     * 用户认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息 数据库获取账号密码
        String name = authenticationToken.getPrincipal().toString();
        User user = new User();
        user.setName(name);
        user.setPassword("123456");
        if (user == null) {
            return null;
        } else {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }

    /**
     * 用户授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户信息 数据库获取账号密码、权限
        String name = (String) principalCollection.getPrimaryPrincipal();

        List roleList = new ArrayList<>();
        Role role = new Role();
        role.setRoleName("admin");
        List permissionList = new ArrayList<>();
        Permission permission = new Permission();
        permission.setPermission("add");
        permissionList.add(permission);
        Permission permission2 = new Permission();
        permission2.setPermission("edit");
        permissionList.add(permission2);
        role.setPermissions(permissionList);
        roleList.add(role);

        Role role2 = new Role();
        role2.setRoleName("user");
        List permissionList2 = new ArrayList<>();
        Permission permission21 = new Permission();
        permission21.setPermission("select");
        permissionList2.add(permission21);
        Permission permission22 = new Permission();
        permission22.setPermission("edit");
        permissionList2.add(permission22);
        role2.setPermissions(permissionList2);
        roleList.add(role2);

        //查询用户名称
        User user = new User();
        user.setName(name);
        user.setRoles(roleList);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role r : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(r.getRoleName());
            for (Permission p : r.getPermissions()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

}
