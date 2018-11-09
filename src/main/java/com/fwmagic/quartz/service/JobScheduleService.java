package com.fwmagic.quartz.service;


import com.fwmagic.quartz.entity.ScheduleJob;
import com.github.pagehelper.PageInfo;

public interface JobScheduleService {

    PageInfo<ScheduleJob> getJobAndTriggerDetails(ScheduleJob scheduleJob, int pageNum, int pageSize);

}
