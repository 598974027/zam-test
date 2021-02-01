package com.example.web_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: SecurityController
 *
 * @author zhangaomin
 * @time 2021/2/1 14:12
 **/
@RestController
@RequestMapping("/security")
public class SecurityController {
    protected final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    /**
     * 查用户集合 不要权限
     *
     * @return
     */
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public AjaxResult listAll() {
        try {
            return AjaxResult.success("查用户集合");
        } catch (Exception e) {
            logger.error("查用户集合异常", e);
            return AjaxResult.error("查用户集合异常");
        }
    }

    /**
     * 查用户集合 要权限
     *
     * @return
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public AjaxResult selectAll() {
        try {
            return AjaxResult.success("查用户集合");
        } catch (Exception e) {
            logger.error("查用户集合异常", e);
            return AjaxResult.error("查用户集合异常");
        }
    }

    /**
     * 查用户
     *
     * @return
     */
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public AjaxResult select() {
        try {
            return AjaxResult.success("查用户");
        } catch (Exception e) {
            logger.error("查用户异常", e);
            return AjaxResult.error("查用户异常");
        }
    }

    /**
     * 新增用户
     *
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public AjaxResult insert() {
        try {
            return AjaxResult.success("新增用户");
        } catch (Exception e) {
            logger.error("新增用户异常", e);
            return AjaxResult.error("新增用户异常");
        }
    }

}
