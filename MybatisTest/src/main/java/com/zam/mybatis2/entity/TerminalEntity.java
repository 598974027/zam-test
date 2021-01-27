package com.zam.mybatis2.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TerminalEntity implements Serializable {

    /**
     * vin
     */
    @Getter
    @Setter
    private String vinno;

    /**
     * 终端号
     */
    @Getter
    @Setter
    private int terminalcode;

    /**
     * 在线状态
     * 1 在线
     * 0 下线
     */
    @Getter
    private int isOnline;

    /**
     * 在线状态方式
     * 0 终端主动
     * 1 DGS判断
     */
    @Getter
    private int onlineType;

    /**
     * 用于标记incom
     */
    @Getter
    private String globalFlag;

    /**
     * MD5
     */
    @Getter
    @Setter
    private String md5Context;

    /**
     * 是否绑定
     */
    @Getter
    @Setter
    private boolean isBinding;

    /**
     * 最后活动时间
     */
    @Getter
    @Setter
    private LocalDateTime lastTime;

    /**
     * 标记，用于刷写redis判断
     */
    @Getter
    @Setter
    private boolean flag;

}
