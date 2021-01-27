package com.zam.mybatis_plus.dao;

import lombok.extern.slf4j.Slf4j;
import com.zam.mybatis_plus.entity.SysUser;
import com.zam.mybatis_plus.mapper.SysUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * 用户信息表(sys_user)数据DAO
 *
 * @author zhangaomin
 * @description Mybatisplus-Code-Generator插件创建
 * @since 2021-01-27 11:12:18
 */
@Slf4j
@Repository
public class SysUserDao extends ServiceImpl<SysUserMapper, SysUser> {

}