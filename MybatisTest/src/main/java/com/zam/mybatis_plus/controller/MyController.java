package com.zam.mybatis_plus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zam.mybatis_plus.entity.SysUser;
import com.zam.mybatis_plus.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/zam")
public class MyController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public List<SysUser> test1() throws Exception {
        return sysUserService.getSysUserListByCompanyId(1);
    }

    /**
     * 自带分页
     *
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public Map<String, Object> test2(@RequestParam(value = "pageNumber", defaultValue = "2", required = false) Integer pageNumber,
                                     @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Page<SysUser> page = new Page<>(pageNumber, pageSize);
        map.put("data", sysUserService.getSysUserListByCompanyId(page, 1));
        return map;
    }

    /**
     * 插件分页
     *
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public Map<String, Object> test3(@RequestParam(value = "pageNumber", defaultValue = "2", required = false) Integer pageNumber,
                                     @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNumber, pageSize);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUserService.getSysUserListByCompanyId(1));
        map.put("data", pageInfo);
        return map;
    }

}
