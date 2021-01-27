package com.zam.mybatis_plus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户信息表(sys_user)实体类
 *
 * @author zhangaomin
 * @description Mybatisplus-Code-Generator插件创建
 * @since 2021-01-27 11:12:18
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends Model<SysUser> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId
    private Integer id;
    /**
     * 公司ID
     */
    private Integer companyId;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 审核状态，1：未审核，2：审核通过，3：审核不通
     */
    private String auditStatus;
    /**
     * 审核人
     */
    private Integer auditBy;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 删除标识，0：未删除；1：已删除
     */
    private String delFlag;
    /**
     * 创建人ID
     */
    private Integer createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人ID
     */
    private Integer updateBy;
    /**
     * 修改时间
     */
    @TableField(update = "now()")
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;

}