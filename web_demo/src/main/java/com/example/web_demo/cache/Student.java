package com.example.web_demo.cache;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/30 22:16
 * @see
 **/
@Data
public class Student implements Serializable {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
