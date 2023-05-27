package com.sky.springbatch.service;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class JobService {

    @Autowired
    JobLauncher jobLauncher;

    @Qualifier("firstJob")
    @Autowired
    private Job firstJob;

    @Qualifier("secondJob")
    @Autowired
    private Job secondJob;

    @Async
    public void startJob(String jobName) throws Exception {
        HashMap<String, JobParameter> jobParameter = new HashMap<>();
        jobParameter.put("Current Time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(jobParameter);
        JobExecution jobExecution = null;
        if (jobName.equalsIgnoreCase("First Job")) {
            jobExecution = jobLauncher.run(firstJob, jobParameters);
        } else if (jobName.equalsIgnoreCase("Second Job")) {
            jobExecution = jobLauncher.run(secondJob, jobParameters);
        }
        System.out.println("Job Execution ID: "+jobExecution.getId());
    }
}
