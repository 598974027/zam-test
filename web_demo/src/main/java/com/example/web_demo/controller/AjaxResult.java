package com.example.web_demo.controller;

import java.util.HashMap;

/**
 * 请求结果类
 */
public class AjaxResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 无参构造
     */
    public AjaxResult() {
    }

    /**
     * @param code 状态码
     * @param msg  返回内容
     */
    public AjaxResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public AjaxResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (data != null) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return
     */
    public static AjaxResult success() {
        return AjaxResult.success("success");
    }

    /**
     * 返回禁止消息
     *
     * @return
     */
    public static AjaxResult forbidden() {
        return AjaxResult.forbidden("forbidden");
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static AjaxResult error() {
        return AjaxResult.error("error");
    }

    /**
     * @param msg 返回内容
     * @return
     */
    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }

    /**
     * @param data 数据对象
     * @return
     */
    public static AjaxResult success(Object data) {
        return AjaxResult.success("success", data);
    }

    /**
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     * @return
     */
    @Deprecated
    public static AjaxResult success(int code, String msg, Object data) {
        return new AjaxResult(code, msg, data);
    }

    /**
     * @param msg 返回内容
     * @return
     */
    public static AjaxResult forbidden(String msg) {
        return AjaxResult.forbidden(msg, null);
    }

    /**
     * @param msg  返回内容
     * @param data 数据对象
     * @return
     */
    public static AjaxResult forbidden(String msg, Object data) {
        return new AjaxResult(HttpStatus.FORBIDDEN, msg, data);
    }

    /**
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     * @return
     */
    @Deprecated
    public static AjaxResult forbidden(int code, String msg, Object data) {
        return new AjaxResult(code, msg, data);
    }

    /**
     * @param msg 返回内容
     * @return
     */
    public static AjaxResult error(String msg) {
        return AjaxResult.error(msg, null);
    }

    /**
     * @param msg  返回内容
     * @param data 数据对象
     * @return
     */
    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     * @return
     */
    @Deprecated
    public static AjaxResult error(int code, String msg, Object data) {
        return new AjaxResult(code, msg, data);
    }
}
