package com.example.web_demo.controller;

import com.example.web_demo.mybatis.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    private TestMapper testMapper;

    public int test1() {
        return testMapper.selectFinishedDbcRecord().size();
    }

}
