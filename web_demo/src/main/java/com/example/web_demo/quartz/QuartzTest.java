//package com.example.web_demo.quartz;
//
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * 功能描述: 任务业务类，继承QuartzJobBean
// *
// * @author zhangam
// * @time 2020/5/7 14:32
// * @see
// **/
//public class QuartzTest extends QuartzJobBean {
//
//    @Override
//    public void executeInternal(JobExecutionContext context) throws JobExecutionException {
//        Object msg = context.getJobDetail().getJobDataMap().get("msg");
//        System.out.println(Thread.currentThread().getName() + " Time :" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " " + msg);
//    }
//
//}