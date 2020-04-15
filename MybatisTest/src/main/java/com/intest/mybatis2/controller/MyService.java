package com.intest.mybatis2.controller;

import com.intest.mybatis2.dao.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    private TestMapper testMapper;

    public int test() {
        return testMapper.selectFinishedDbcRecord().size();
    }

}
