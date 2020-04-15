package com.intest.mybatis2.controller;

import com.intest.mybatis2.dao.TestMapper;
import com.intest.mybatis2.entity.DbcMd5RecordEntity;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyService {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int test1() {
        return testMapper.selectFinishedDbcRecord().size();
    }

    public DbcMd5RecordEntity test2(String md5) {
        return testMapper.selectByMd52(md5);
    }

    @Transactional
    public List<DbcMd5RecordEntity> test3(String md5) {
        return testMapper.selectByMd53(md5);
    }

}
