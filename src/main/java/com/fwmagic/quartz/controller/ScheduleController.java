package com.fwmagic.quartz.controller;

import com.fwmagic.quartz.entity.ResponseEnum;
import com.fwmagic.quartz.entity.ResponseResult;
import com.fwmagic.quartz.entity.ScheduleJob;
import com.fwmagic.quartz.schedule.JobSchedulerManager;
import com.fwmagic.quartz.service.JobScheduleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:wei.fang
 * @Description:任务调度管理
 */
@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private JobScheduleService jobScheduleService;

    @Autowired
    private JobSchedulerManager jobSchedulerManager;

    @ApiOperation(value = "添加调度任务")
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {"application/json"})
    public ResponseResult addJob(ScheduleJob job) throws Exception {
        try {
            jobSchedulerManager.addJob(job.getJobName(), job.getJobGroup(), job.getCronExpression());
            return new ResponseResult(ResponseEnum.OK.getCode(), ResponseEnum.OK.getValue(), null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            return new ResponseResult(ResponseEnum.BAD_OPERATE.getCode(), ResponseEnum.BAD_OPERATE.getValue(), null);
        }
    }

    @ApiOperation(value = "暂停调度任务")
    @RequestMapping(value = "/pause", method = {RequestMethod.POST}, produces = {"application/json"})
    public ResponseResult pauseJob(ScheduleJob job) throws Exception {
        try {
            jobSchedulerManager.jobPause(job.getJobName(), job.getJobGroup());
            return new ResponseResult(ResponseEnum.OK.getCode(), ResponseEnum.OK.getValue(), null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            return new ResponseResult(ResponseEnum.BAD_OPERATE.getCode(), ResponseEnum.BAD_OPERATE.getValue(), null);
        }
    }

    @ApiOperation(value = "重新启动调度任务")
    @RequestMapping(value = "/resume", method = {RequestMethod.POST}, produces = {"application/json"})
    public ResponseResult resumeJob(ScheduleJob job) throws Exception {
        try {
            jobSchedulerManager.jobResume(job.getJobName(), job.getJobGroup());
            return new ResponseResult(ResponseEnum.OK.getCode(), ResponseEnum.OK.getValue(), null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            return new ResponseResult(ResponseEnum.BAD_OPERATE.getCode(), ResponseEnum.BAD_OPERATE.getValue(), null);
        }
    }

    @ApiOperation(value = "修改调度任务")
    @RequestMapping(value = "/reschedule", method = {RequestMethod.POST}, produces = {"application/json"})
    public ResponseResult rescheduleJob(ScheduleJob job) throws Exception {
        try {
            jobSchedulerManager.jobReschedule(job.getJobName(), job.getJobGroup(), job.getCronExpression());
            return new ResponseResult(ResponseEnum.OK.getCode(), ResponseEnum.OK.getValue(), null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            return new ResponseResult(ResponseEnum.BAD_OPERATE.getCode(), ResponseEnum.BAD_OPERATE.getValue(), null);
        }
    }

    @ApiOperation(value = "删除调度任务")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST}, produces = {"application/json"})
    public ResponseResult deleteJob(ScheduleJob job) throws Exception {
        try {
            jobSchedulerManager.jobDelete(job.getJobName(), job.getJobGroup());
            return new ResponseResult(ResponseEnum.OK.getCode(), ResponseEnum.OK.getValue(), null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            return new ResponseResult(ResponseEnum.BAD_OPERATE.getCode(), ResponseEnum.BAD_OPERATE.getValue(), null);
        }
    }

    @ApiOperation(value = "立即执行调度任务")
    @RequestMapping(value = "/trigger", method = {RequestMethod.POST}, produces = {"application/json"})
    public ResponseResult triggerJob(ScheduleJob job) throws Exception {
        try {
            jobSchedulerManager.triggerJob(job.getJobName(), job.getJobGroup());
            return new ResponseResult(ResponseEnum.OK.getCode(), ResponseEnum.OK.getValue(), null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            return new ResponseResult(ResponseEnum.BAD_OPERATE.getCode(), ResponseEnum.BAD_OPERATE.getValue(), null);
        }
    }

    @ApiOperation(value = "调度任务分页列表")
    @RequestMapping(value = {"/all"}, method = {RequestMethod.POST,RequestMethod.GET}, produces = {"application/json"})
    public ResponseResult page(ScheduleJob scheduleJob, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        try {
            PageInfo<ScheduleJob> jobAndTrigger = jobScheduleService.getJobAndTriggerDetails(scheduleJob, pageNum, pageSize);
            return new ResponseResult(ResponseEnum.OK.getCode(), ResponseEnum.OK.getValue(), jobAndTrigger);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            return new ResponseResult(ResponseEnum.BAD_OPERATE.getCode(), ResponseEnum.BAD_OPERATE.getValue(), null);
        }
    }
}
