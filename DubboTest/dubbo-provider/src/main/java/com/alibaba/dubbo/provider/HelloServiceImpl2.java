package com.alibaba.dubbo.provider;

import com.alibaba.dubbo.api.HelloService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2020/5/3 11:37
 * @see
 **/
@Service(interfaceClass = HelloService.class, timeout = 10000)
@Component
public class HelloServiceImpl2 implements HelloService {

    public String sayHello(String name) {
        System.out.println("version  " + name);
        return "hello, " + name;
    }

}
