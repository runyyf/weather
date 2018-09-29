package com.hugo.weather.config;

import com.hugo.weather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {
//    请求间隔时间
    private final static int TIME = 1800;
    //    //JobDetail
//    public JobDetail weatherDataSyncJobDetail(){
//        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJobDetail").storeDurably()
//                .build();
//    }
//
//    //trigger
//    @Bean()
//    public Trigger weatherDataSyncTrigger(){
//        SimpleScheduleBuilder scheduleBuilder =  SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever();
//        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail()).withIdentity("weatherDataSyncTrigger")
//                .withSchedule(scheduleBuilder).build();
//    }
//
    //创建调度器
    public Scheduler getScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory.getScheduler();
    }

    @Bean
    public Scheduler schedulerJob() throws SchedulerException {
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("job1").build();
        //创建触发器 每TIME秒钟执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(TIME).repeatForever())
                .build();
        Scheduler scheduler = getScheduler();
        //将任务及其触发器放入调度器
        scheduler.scheduleJob(jobDetail, trigger);
        //调度器开始调度任务
        scheduler.start();
        return scheduler;
    }
}
