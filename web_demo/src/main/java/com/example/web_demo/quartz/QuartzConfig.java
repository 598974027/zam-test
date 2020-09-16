//package com.example.web_demo.quartz;
//
//import org.quartz.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 功能描述: Quartz的配置类
// *
// * @author zhangam
// * @time 2020/5/7 14:35
// * @see
// **/
//@Configuration
//public class QuartzConfig {
//
//    @Bean
//    public JobDetail printTimeJobDetail() {
//        return JobBuilder.newJob(QuartzTest.class)
//                .usingJobData("msg", "Hello Quartz").storeDurably()
//                .withIdentity("printTimeJobDetail").build();
//    }
//
//    @Bean
//    public Trigger printTimeTrigger() {
//        return TriggerBuilder.newTrigger().forJob(printTimeJobDetail())
                /**
                 * 1.Seconds (0~59)
                 * 2.Minutes (0~59)
                 * 3.Hours (0~23)
                 * 4.Day-of-Month (1~31,但是要注意有些月份没有31天)
                 * 5.Month (0~11，或者"JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV,DEC")
                 * 6.Day-of-Week (1~7,1=SUN 或者"SUN, MON, TUE, WED, THU, FRI, SAT”)
                 * 7.Year (1970~2099)
                 *
                 * *：代表所有可能的值。因此，“*”在Month中表示每个月，在Day-of-Month中表示每天，在Hours表示每小时
                 * - ：表示指定范围。
                 * , ：表示列出枚举值。例如：在Minutes子表达式中，“5,20”表示在5分钟和20分钟触发。
                 * / ：被用于指定增量。例如：在Minutes子表达式中，“0/15”表示从0分钟开始，每15分钟执行一次。"3/20"表示从第三分钟开始，每20分钟执行一次。和"3,23,43"（表示第3，23，43分钟触发）的含义一样。
                 * ? ：用在Day-of-Month和Day-of-Week中，指“没有具体的值”。当两个子表达式其中一个被指定了值以后，为了避免冲突，需要将另外一个的值设为“?”。例如：想在每月20日触发调度，不管20号是星期几，只能用如下写法：0 0 0 20 * ?，其中最后以为只能用“?”，而不能用“*”。
                 * L ：用在day-of-month和day-of-week字串中。它是单词“last”的缩写。它在两个子表达式中的含义是不同的。
                 * 在day-of-month中，“L”表示一个月的最后一天，一月31号，3月30号。
                 * 在day-of-week中，“L”表示一个星期的最后一天，也就是“7”或者“SAT”
                 * 如果“L”前有具体内容，它就有其他的含义了。例如：“6L”表示这个月的倒数第六天。“FRIL”表示这个月的最后一个星期五。
                 * 注意：在使用“L”参数时，不要指定列表或者范围，这样会出现问题。
                 * W ：“Weekday”的缩写。只能用在day-of-month字段。用来描叙最接近指定天的工作日（周一到周五）。例如：在day-of-month字段用“15W”指“最接近这个月第15天的工作日”，即如果这个月第15天是周六，那么触发器将会在这个月第14天即周五触发；如果这个月第15天是周日，那么触发器将会在这个月第 16天即周一触发；如果这个月第15天是周二，那么就在触发器这天触发。注意一点：这个用法只会在当前月计算值，不会越过当前月。“W”字符仅能在 day-of-month指明一天，不能是一个范围或列表。也可以用“LW”来指定这个月的最后一个工作日，即最后一个星期五。
                 * # ：只能用在day-of-week字段。用来指定这个月的第几个周几。例：在day-of-week字段用"6#3" or "FRI#3"指这个月第3个周五（6指周五，3指第3个）。如果指定的日期不存在，触发器就不会触发。
                 */
//                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
//                .withIdentity("printTimeTrigger").build();
//    }
//
//}