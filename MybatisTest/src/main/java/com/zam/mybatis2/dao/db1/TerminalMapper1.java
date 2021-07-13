package com.zam.mybatis2.dao.db1;

import com.zam.mybatis2.entity.DbcUsedRecord;
import com.zam.mybatis2.entity.TerminalEntity;

import java.util.List;
import java.util.Map;

public interface TerminalMapper1 {
    /**
     * 更新终端基础信息
     *
     * @return
     */
    List<Map<String, Object>> getTerminalInfo();

    /**
     * 通过终端号更新终端基础信息
     *
     * @return
     */
    Map<String, Object> getTerminalInfoById(String tcode);

    /**
     * 更新终端上下线
     *
     * @return
     */
    void updateTerminalOnline(TerminalEntity terminalEntity);

    /**
     * 新增终端上下线记录
     *
     * @return
     */
    void insertTerminalOnline(TerminalEntity terminalEntity);

    /**
     * 加载已完成DBC请求信息
     *
     * @return
     */
    List<Map<String, Object>> getFinishedDbcUsedRecord();

    /**
     * 通过md5Code查询DBC是否请求完
     *
     * @return
     */
    Map<String, Object> getFinishedDbcById(String md5Code);

    /**
     * 新增DBC使用记录
     *
     * @return
     */
    void insertDbcUsedRecord(DbcUsedRecord dbcUsedRecord);
}
