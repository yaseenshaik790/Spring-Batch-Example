package com.sky.springbatch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {


    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before Job: " +jobExecution.getJobInstance().getJobName());
        System.out.println("Before Job params : " +jobExecution.getJobParameters());
        System.out.println("Before Job execution : " +jobExecution.getExecutionContext());
        jobExecution.getExecutionContext().put("test", "Yaseen");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After Job: " +jobExecution.getJobInstance().getJobName());
        System.out.println("After Job params : " +jobExecution.getJobParameters());
        System.out.println("After Job execution : " +jobExecution.getExecutionContext());
    }
}
