package com.example.web_demo.entity;

import java.time.LocalDateTime;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/8/20 10:29
 * @see
 **/
public class DbcMd5RecordEntity {

    /**
     * DbcId
     */
    private Integer dbcId;
    /**
     * md5值
     */
    private String md5;

    /**
     * DBC文件名
     */
    private String fileName;

    /**
     * DBC全名
     */
    private String fullName;

    /**
     * 变量数
     */
    private Integer variablesCount;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    public Integer getDbcId() {
        return dbcId;
    }

    public void setDbcId(Integer dbcId) {
        this.dbcId = dbcId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getVariablesCount() {
        return variablesCount;
    }

    public void setVariablesCount(Integer variablesCount) {
        this.variablesCount = variablesCount;
    }

}
