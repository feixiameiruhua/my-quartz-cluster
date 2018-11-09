package com.fwmagic.quartz.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.core.QuartzSchedulerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class SchedulerExecJob implements Job {

//    QuartzSchedulerThread

    private static Logger logger = LoggerFactory.getLogger(SchedulerExecJob.class);

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        String jobName = context.getJobDetail().getKey().getName();
        switch (jobName) {
            /*每5s执行一次*/
            case "quartz_test1":
                System.err.println(getAddress() + " " + getDate() + "====>quartz_test1<====");
                break;
            /*每5s执行一次*/
            case "quartz_test2":
                System.err.println(getAddress() + " " + getDate() + "====>quartz_test2<====");
                break;
            /*每5s执行一次*/
            case "quartz_test3":
                System.err.println(getAddress() + " " + getDate() + "====>quartz_test3<====");
                break;
            default:
                System.err.println(getAddress() + " " + getDate() + "====>other task<====");
                break;
        }
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getAddress() {
        return "http://localhost:" + ResourceBundle.getBundle("application").getString("server.port");
    }
}
