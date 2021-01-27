package com.zam.mybatis_plus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zam.mybatis_plus.entity.SysUser;

import java.util.List;

/**
 * 用户信息表服务接口
 *
 * @author zhangaomin
 * @description Mybatisplus-Code-Generator插件创建
 * @since 2021-01-27 11:12:18
 */
public interface SysUserService {

    /**
     * 查用户集合
     *
     * @param companyId
     * @return
     * @throws Exception
     */
    List<SysUser> getSysUserListByCompanyId(Integer companyId) throws Exception;

    /**
     * 查用户集合
     *
     * @param page
     * @param companyId
     * @return
     * @throws Exception
     */
    IPage<SysUser> getSysUserListByCompanyId(Page page, Integer companyId) throws Exception;

}
