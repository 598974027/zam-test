package com.example.web_demo.dao;

import com.example.web_demo.entity.SysRegionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
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
     * selectAllRecord
     *
     * @return
     */
    List<SysRegionEntity> selectAllRecord();

    /**
     * selectAllRecordFir
     *
     * @return
     */
    List<SysRegionEntity> selectAllRecordFir();

    /**
     * selectAllRecordByCode
     *
     * @param code
     * @return
     */
    List<SysRegionEntity> selectAllRecordByCode(@Param("code") String code);

    /**
     * getRecordByCode
     *
     * @param code
     * @return
     */
    SysRegionEntity getRecordByCode(@Param("code") String code);

    /**
     * updateRecord
     *
     * @param sysRegionEntity
     */
    void updateRecord(SysRegionEntity sysRegionEntity);
}
