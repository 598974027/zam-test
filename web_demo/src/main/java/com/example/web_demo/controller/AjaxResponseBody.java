package com.example.web_demo.controller;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能描述: AjaxResponseBody
 *
 * @author zhangaomin
 * @time 2020/9/17 14:29
 **/
@Data
public class AjaxResponseBody implements Serializable {

    private String status;

    private String msg;

    private Object result;

    private String jwtToken;

}
