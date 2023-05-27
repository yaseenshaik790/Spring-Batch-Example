package com.sky.springbatch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Before Step: " +stepExecution.getStepName());
        System.out.println("Before Step params : " +stepExecution.getJobParameters());
        System.out.println("Before Step execution : " +stepExecution.getExecutionContext());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("After Step: " +stepExecution.getStepName());
        System.out.println("After Step params : " +stepExecution.getJobParameters());
        System.out.println("After Step execution : " +stepExecution.getExecutionContext());
        return null;
    }
}
