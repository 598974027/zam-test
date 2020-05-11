package com.alibaba.dubbo.consumer;

import com.alibaba.dubbo.api.HelloService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2020/5/3 11:37
 * @see
 **/
@RestController
public class HelloController {

//    @Reference(url = "dubbo://127.0.0.1:20881")
    @Reference(version = "1.0.0", timeout = 10000, retries = 1)
    private HelloService helloService;

    @RequestMapping(value = "/hello")
    public String hello() {
        String hello = helloService.sayHello("zam");
        System.out.println(hello);
        return hello;
    }

}
