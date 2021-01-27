package com.zam.mybatis2.entity;

import lombok.Data;

@Data
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
//    private LocalDateTime createTime;
    private String createTime;

}
