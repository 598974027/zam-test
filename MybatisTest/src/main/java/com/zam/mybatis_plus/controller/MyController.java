package com.zam.mybatis_plus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zam.mybatis_plus.entity.SysDept;
import com.zam.mybatis_plus.service.SysDeptService;
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
    private SysDeptService sysUserService;

    /**
     * 自带分页
     *
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public Map<String, Object> test1(@RequestParam(value = "pageNumber", defaultValue = "2", required = false) Integer pageNumber,
                                     @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Page<SysDept> page = new Page<>(pageNumber, pageSize);
        map.put("data", sysUserService.getList(page, "1"));
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
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public Map<String, Object> test2(@RequestParam(value = "pageNumber", defaultValue = "2", required = false) Integer pageNumber,
                                     @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNumber, pageSize);
        PageInfo<SysDept> pageInfo = new PageInfo<>(sysUserService.getList("1"));
        map.put("data", pageInfo);
        return map;
    }

    @RequestMapping(value = "/test11", method = RequestMethod.GET)
    public String test11() throws Exception {
        sysUserService.selectDeptById(1L);
        return "test11";
    }

    @RequestMapping(value = "/test12", method = RequestMethod.GET)
    public String test12() throws Exception {
        sysUserService.selectDeptById2(1L);
        return "test12";
    }

    @RequestMapping(value = "/test13", method = RequestMethod.GET)
    public String test13() throws Exception {
        sysUserService.selectDeptById3(1L, 5L);
        return "test13";
    }

    @RequestMapping(value = "/test21", method = RequestMethod.GET)
    public String test21() throws Exception {
        sysUserService.updateDeptById(1L);
        return "test21";
    }

    @RequestMapping(value = "/test22", method = RequestMethod.GET)
    public String test22() throws Exception {
        sysUserService.updateDeptById2(1L);
        return "test22";
    }

    @RequestMapping(value = "/test23", method = RequestMethod.GET)
    public String test23() throws Exception {
        sysUserService.updateDeptById3(3L, 5L);
        return "test23";
    }

    @RequestMapping(value = "/test24", method = RequestMethod.GET)
    public String test24() throws Exception {
        sysUserService.updateDeptByName("zam");
        return "test24";
    }

}
