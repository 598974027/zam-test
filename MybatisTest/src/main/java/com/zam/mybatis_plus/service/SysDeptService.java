package com.zam.mybatis_plus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zam.mybatis_plus.entity.SysDept;

import java.util.List;

public interface SysDeptService extends IService<SysDept> {
    /**
     * @param isEnable
     * @return
     * @throws Exception
     */
    List<SysDept> getList(String isEnable) throws Exception;

    /**
     * @param page
     * @param isEnable
     * @return
     * @throws Exception
     */
    IPage<SysDept> getList(Page page, String isEnable) throws Exception;

    /**
     * @param id
     * @return
     * @throws Exception
     */
    SysDept selectDeptById(Long id) throws Exception;

    /**
     * @param id
     * @return
     * @throws Exception
     */
    SysDept selectDeptById2(Long id) throws Exception;

    /**
     * @param startId
     * @param endId
     * @return
     * @throws Exception
     */
    List<SysDept> selectDeptById3(Long startId, Long endId) throws Exception;

    /**
     * @param name
     * @return
     * @throws Exception
     */
    List<SysDept> selectDeptByName(String name) throws Exception;

    /**
     * @param id
     * @return
     * @throws Exception
     */
    Boolean updateDeptById(Long id) throws Exception;

    /**
     * @param id
     * @return
     * @throws Exception
     */
    Boolean updateDeptById2(Long id) throws Exception;

    /**
     * @param startId
     * @param endId
     * @return
     * @throws Exception
     */
    Boolean updateDeptById3(Long startId, Long endId) throws Exception;

    /**
     * @param name
     * @return
     * @throws Exception
     */
    Boolean updateDeptByName(String name) throws Exception;
}
