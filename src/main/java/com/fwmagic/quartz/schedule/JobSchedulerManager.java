package com.fwmagic.quartz.schedule;


import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class JobSchedulerManager {

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    public void addJob(String job_Name, String job_Group, String cronExpression) throws Exception {

        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(SchedulerExecJob.class).withIdentity(job_Name, job_Group).build();
        //指在集群中，一个scheduler执行job失败，将会被另外一个scheduler执行
        /*jobDetail.requestsRecovery();*/

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(job_Name, job_Group)
                .withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new Exception("创建定时任务失败!",e);
        }
    }


    public void jobPause(String job_Name, String job_Group) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(job_Name, job_Group));
    }


    public void jobResume(String job_Name, String job_Group) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(job_Name, job_Group));
    }


    public void jobReschedule(String job_Name, String job_Group, String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(job_Name, job_Group);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new Exception("更新定时任务失败!",e);
        }
    }

    public void jobDelete(String job_Name, String job_Group) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(job_Name, job_Group));
        scheduler.unscheduleJob(TriggerKey.triggerKey(job_Name, job_Group));
        scheduler.deleteJob(JobKey.jobKey(job_Name, job_Group));
    }


    public void triggerJob(String job_Name, String job_Group) throws Exception {
        scheduler.triggerJob(JobKey.jobKey(job_Name, job_Group));
    }

}
