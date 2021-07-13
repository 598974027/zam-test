package com.zam.mybatis_plus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;

@Data
@TableName("sys_dept")
public class SysDept implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    private Integer parentId;
    private String ancestors;
    private String name;
    private Long orderNum;
    private String leader;
    private String phone;
    private String isEnable;
    private String isDel;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String remark;
}