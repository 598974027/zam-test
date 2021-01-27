package com.zam.mybatis2.dao.db1;

import com.zam.mybatis2.entity.DbcMd5RecordEntity;

import java.util.List;
import java.util.Set;

public interface DbcMd5RecordMapper {

    /**
     * 查询所有DBC已完成的MD5记录
     *
     * @return
     */
    public Set<String> selectFinishedDbcRecord();

    /**
     * 根据md5值查询DbcMd5Record
     *
     * @param md5
     * @return
     */
    public DbcMd5RecordEntity selectByMd5(String md5);

    /**
     * 根据md5值查询DbcMd5Record
     *
     * @param md5
     * @return
     */
    public DbcMd5RecordEntity selectByMd52(String md5);

    /**
     * 根据md5值查询DbcMd5Record
     *
     * @param md5
     * @return
     */
    public List<DbcMd5RecordEntity> selectByMd53(String md5);

}
