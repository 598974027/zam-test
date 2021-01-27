package com.zam.mybatis2.entity;

import java.time.LocalDateTime;

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

    public long getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(long terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getMd5Code() {
        return md5Code;
    }

    public void setMd5Code(String md5Code) {
        this.md5Code = md5Code;
    }

    public LocalDateTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalDateTime travelTime) {
        this.travelTime = travelTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
