package com.example.web_demo.schedule;

import org.springframework.stereotype.Component;

/**
 * 功能描述:
 * <p>
 * 三种：
 * 1) Java自带的java.util.Timer类，这个类允许你调度一个java.util.TimerTask任务。 最早的时候就是这样写定时任务的。
 * 2) 开源的第三方框架： Quartz 或者 elastic-job ， 但是这个比较复杂和重量级，适用于分布式场景下的定时任务，可以根据需要多实例部署定时任务。
 * 3) 使用Spring提供的注解： @Schedule 。 如果定时任务执行时间较短，并且比较单一，可以使用这个注解。
 * <p>
 * 存在两种调度方式： 单线程和多线程
 * 串行方式 不需配置
 * 并行方式 当定时任务很多的时候，为了提高任务执行效率，可以采用并行方式执行定时任务，任务之间互不影响，只要实现SchedulingConfigurer接口就可以。
 *
 * @author zhangam
 * @time 2019/6/17 14:26
 * @see
 **/
@Component
public class ScheduledTest {

    //    @Scheduled(fixedDelay = 1000)
    public void test1() {
        System.out.println(Thread.currentThread().getName() + "--test1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "--test1");
    }

    //    @Scheduled(fixedDelay = 1000)
    public void test2() {
        System.out.println(Thread.currentThread().getName() + "--test2");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "--test2");
    }

}