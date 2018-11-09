package com.fwmagic.quartz.mapper;


import com.fwmagic.quartz.entity.ScheduleJob;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JobAndTriggerMapper {

    List<ScheduleJob> getJobAndTriggerDetails(@Param("scheduleJob") ScheduleJob scheduleJob);

}
