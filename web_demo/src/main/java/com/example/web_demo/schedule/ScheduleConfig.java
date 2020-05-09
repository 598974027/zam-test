package com.example.web_demo.schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

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
        return Executors.newScheduledThreadPool(10, new ThreadFactory() {
            AtomicInteger atomic = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "schedule任务调度" + this.atomic.getAndIncrement());
            }
        });
    }

}
