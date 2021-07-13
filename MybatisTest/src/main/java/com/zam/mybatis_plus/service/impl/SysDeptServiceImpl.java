package com.zam.mybatis_plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zam.mybatis_plus.entity.SysDept;
import com.zam.mybatis_plus.mapper.SysDeptMapper;
import lombok.extern.slf4j.Slf4j;
import com.zam.mybatis_plus.service.SysDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Slf4j
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public List<SysDept> getList(String isEnable) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_enable", isEnable);
        return this.list(queryWrapper);
    }

    @Override
    public IPage<SysDept> getList(Page page, String isEnable) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_enable", isEnable);
        return this.page(page, queryWrapper);
    }

    @Override
    public SysDept selectDeptById(Long id) throws Exception {
        SysDept sysDept = this.getById(id);
//        int a = 1 / 0;
        return sysDept;
    }

    @Override
    @Transactional
    public SysDept selectDeptById2(Long id) throws Exception {
        SysDept sysDept = this.getById(id);
//        int a = 1 / 0;
        return sysDept;
    }

    @Override
    @Transactional
    public List<SysDept> selectDeptById3(Long startId, Long endId) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ge("id", startId);
        queryWrapper.le("id", endId);
        List<SysDept> list = this.list(queryWrapper);
        return list;
    }

    @Override
    public List<SysDept> selectDeptByName(String name) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", name);
        List<SysDept> list = this.list(queryWrapper);
        return list;
    }

    @Override
    public Boolean updateDeptById(Long id) throws Exception {
        SysDept sysDept = new SysDept();
        sysDept.setId(id);
        sysDept.setLeader("123");
        this.updateById(sysDept);
//        int a = 1 / 0;
        return true;
    }

    @Override
    @Transactional(rollbackFor = ArithmeticException.class)
    public Boolean updateDeptById2(Long id) throws Exception {
        try {
            SysDept sysDept = new SysDept();
            sysDept.setId(id);
            sysDept.setLeader("456");
            this.updateById(sysDept);
//            int a = 1 / 0;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    @Transactional//(isolation = Isolation.READ_COMMITTED)
    public Boolean updateDeptById3(Long startId, Long endId) throws Exception {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.ge("id", startId);
        updateWrapper.le("id", endId);
        updateWrapper.set("leader", "abcdef");
        this.update(updateWrapper);
        return true;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Boolean updateDeptByName(String name) throws Exception {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("name", name);
        updateWrapper.set("leader", "abcdef");
        this.update(updateWrapper);
        return true;
    }
}