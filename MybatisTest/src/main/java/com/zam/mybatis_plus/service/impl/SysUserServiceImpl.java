package com.zam.mybatis_plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zam.mybatis_plus.dao.SysUserDao;
import com.zam.mybatis_plus.entity.SysUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.zam.mybatis_plus.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息表服务接口实现
 *
 * @author zhangaomin
 * @description Mybatisplus-Code-Generator插件创建
 * @since 2021-01-27 11:12:18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl implements SysUserService {
    private final SysUserDao sysUserDao;

    @Override
    public List<SysUser> getSysUserListByCompanyId(Integer companyId) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("company_id", companyId);
        return sysUserDao.list(queryWrapper);
    }

    @Override
    public IPage<SysUser> getSysUserListByCompanyId(Page page, Integer companyId) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("company_id", companyId);
        return sysUserDao.page(page, queryWrapper);
    }
}