package com.example.web_demo.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能描述: 任务业务类，继承QuartzJobBean
 *
 * @author zhangam
 * @time 2020/5/7 14:32
 * @see
 **/
public class PrintTimeJob extends QuartzJobBean {

    @Override
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //获取JobDetail中关联的数据
        String msg = (String) context.getJobDetail().getJobDataMap().get("msg");
        System.out.println("current time :" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "---" + msg);
    }

}