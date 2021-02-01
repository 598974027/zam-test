//package com.example.web_demo.security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.io.Serializable;
//import java.util.Collection;
//
///**
// * 功能描述: SysUser
// *
// * @author zhangaomin
// * @time
// **/
//public class SysUser implements Serializable, UserDetails {
//    private static final long serialVersionUID = 1L;
//
//    private transient Collection<? extends GrantedAuthority> authorities;
//
//    /**
//     * 用户ID
//     */
//    private Integer id;
//
//    /**
//     * 公司ID
//     */
//    private Integer companyId;
//
//    /**
//     * 部门ID
//     */
//    private Integer deptId;
//
//    /**
//     * 用户账号
//     */
//    private String username;
//
//    /**
//     * 密码
//     */
//    private String password;
//
//    /**
//     * 菜单角色ID
//     */
//    private Integer menuRoleId;
//
//    /**
//     * 接口角色ID
//     */
//    private Integer apiRoleId;
//
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getCompanyId() {
//        return companyId;
//    }
//
//    public void setCompanyId(Integer companyId) {
//        this.companyId = companyId;
//    }
//
//    public Integer getDeptId() {
//        return deptId;
//    }
//
//    public void setDeptId(Integer deptId) {
//        this.deptId = deptId;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Integer getMenuRoleId() {
//        return menuRoleId;
//    }
//
//    public void setMenuRoleId(Integer menuRoleId) {
//        this.menuRoleId = menuRoleId;
//    }
//
//    public Integer getApiRoleId() {
//        return apiRoleId;
//    }
//
//    public void setApiRoleId(Integer apiRoleId) {
//        this.apiRoleId = apiRoleId;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
