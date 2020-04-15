package com.intest.mybatis2.dao;

import com.intest.mybatis2.entity.DbcMd5RecordEntity;

import java.util.List;
import java.util.Set;

public interface TestMapper {

    /**
     * 查询所有DBC已完成的MD5记录
     *
     * @return
     */
    Set<String> selectFinishedDbcRecord();

    /**
     * 根据md5值查询DbcMd5Record
     *
     * @param md5
     * @return
     */
    DbcMd5RecordEntity selectByMd5(String md5);

    /**
     * 根据md5值查询DbcMd5Record
     *
     * @param md5
     * @return
     */
    DbcMd5RecordEntity selectByMd52(String md5);

    /**
     * 根据md5值查询DbcMd5Record
     *
     * @param md5
     * @return
     */
    List<DbcMd5RecordEntity> selectByMd53(String md5);

}
