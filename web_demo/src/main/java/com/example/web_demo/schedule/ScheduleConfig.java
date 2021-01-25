package com.example.web_demo.schedule;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 功能描述: 配置定时任务线程池
 *
 * @author zhangam
 * @time 2019/6/4 20:15
 * @see
 **/
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(myTaskExecutor());
    }

    @Bean(destroyMethod = "shutdown")
    public ScheduledExecutorService myTaskExecutor() {
        return new ScheduledThreadPoolExecutor(10, new BasicThreadFactory.Builder().namingPattern("定时任务线程-%d").daemon(true).build());
    }

}
