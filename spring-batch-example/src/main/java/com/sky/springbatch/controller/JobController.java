package com.sky.springbatch.controller;

import com.sky.springbatch.service.JobService;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobOperator jobOperator;

    @GetMapping("/api/job/start/{jobName}")
    public String startJob(@PathVariable String jobName) throws Exception {
        jobService.startJob(jobName);
        return "Job Started....";
    }

    @GetMapping("/api/job/stop/{jobExecutionId}")
    public String stopJob(@PathVariable Long jobExecutionId) throws Exception {
        jobOperator.stop(jobExecutionId);
        return "Job Stopped....";
    }
}
