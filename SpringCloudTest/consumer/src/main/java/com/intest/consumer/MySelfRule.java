package com.intest.consumer;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: 自定义负载均衡算法
 *
 * @author zhangam
 * @time 2020/5/11 16:27
 * @see
 **/
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
//        return new RoundRobinRule();//轮询算法
//        return new RandomRule();//随机算法
        return new RandomRule_ZY();
    }

}
