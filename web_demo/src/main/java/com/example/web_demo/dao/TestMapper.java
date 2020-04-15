package com.example.web_demo.dao;

import com.example.web_demo.entity.DbcMd5RecordEntity;

import java.util.Set;

/**
 * 功能描述: Mapper
 *
 * @author zhangam
 * @time 2019/8/20 10:27
 * @see
 **/
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

}
