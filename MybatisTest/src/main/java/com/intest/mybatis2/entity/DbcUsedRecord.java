package com.intest.mybatis2.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DbcUsedRecord {

    /**
     * 终端号
     */
    private long terminalCode;

    /**
     * MD5值
     */
    private String md5Code;

    /**
     * 时间
     */
    private LocalDateTime travelTime;

    /**
     * 状态 1表示开始，2表示结束
     */
    private int status;

}
