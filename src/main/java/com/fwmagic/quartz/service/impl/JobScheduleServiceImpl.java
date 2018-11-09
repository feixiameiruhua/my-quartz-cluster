package com.fwmagic.quartz.service.impl;

import com.fwmagic.quartz.entity.ScheduleJob;
import com.fwmagic.quartz.mapper.JobAndTriggerMapper;
import com.fwmagic.quartz.service.JobScheduleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:wei.fang
 * @Description:
 * @Date:Create in 2018/11/7
 */
@Service
public class JobScheduleServiceImpl implements JobScheduleService {

    @Autowired
    private JobAndTriggerMapper mapper;

    @Override
    public PageInfo<ScheduleJob> getJobAndTriggerDetails(ScheduleJob scheduleJob, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ScheduleJob> list = mapper.getJobAndTriggerDetails(scheduleJob);
        PageInfo<ScheduleJob> page = new PageInfo<ScheduleJob>(list);
        return page;
    }
}
