package com.example.web_demo.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/6/17 16:07
 * @see
 **/
@Component
public class CacheTest {

    //能够根据方法的请求参数对其结果进行缓存
    @Cacheable(cacheNames = "s", key = "#id")
    public Student getUser(int id) {
        System.out.println("getUser");
        Student student = new Student();
        student.setId(1);
        student.setName("zam");
        return new Student();
    }

    //能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用 。
    @CachePut(cacheNames = "s", key = "#s.id")
    public Student updateUser(Student s) {
        System.out.println("updateUser");
        return new Student();
    }

    //清除一条缓存，key为要清空的数据
    @CacheEvict(value = "s", key = "#id")
    public void delect(int id) {
        System.out.println("delect");
    }

    //方法调用后清空所有缓存
    @CacheEvict(value = "s", allEntries = true)
    public void delectAll() {
        System.out.println("delectAll");
    }

    //方法调用前清空所有缓存
    @CacheEvict(value = "s", beforeInvocation = true)
    public void delectAllBefore() {
        System.out.println("delectAllBefore");
    }

}
