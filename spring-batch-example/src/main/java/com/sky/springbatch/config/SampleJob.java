package com.sky.springbatch.config;

import com.sky.springbatch.listener.FirstJobListener;
import com.sky.springbatch.listener.FirstStepListener;
import com.sky.springbatch.processor.FirstItemProcessor;
import com.sky.springbatch.reader.FirstItemReader;
import com.sky.springbatch.service.SecondTask;
import com.sky.springbatch.writer.FirstItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.jsr.launch.JsrJobOperator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.batch.operations.JobOperator;

@Configuration
public class SampleJob {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SecondTask secondTask;

    @Autowired
    private FirstJobListener firstJobListener;

    @Autowired
    private FirstStepListener firstStepListener;
    @Autowired
    private FirstItemProcessor firstItemProcessor;
    @Autowired
    private FirstItemWriter firstItemWriter;
    @Autowired
    private FirstItemReader  firstItemReader;

    @Bean
    public Job firstJob(){
        return jobBuilderFactory.get("First Job")
                .incrementer(new RunIdIncrementer())
                .start(firstStep())
                .next(secondStep())
                .listener(firstJobListener)
                .build();
    }

    public Step firstStep(){
        return stepBuilderFactory.get("First Step")
                .tasklet(firstTaskLet())
                .listener(firstStepListener)
                .build();
    }

    public Tasklet firstTaskLet(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
                System.out.println("First Task");
                return RepeatStatus.FINISHED;
            }
        };
    }

    public Step secondStep(){
        return stepBuilderFactory.get("Second Step")
                .tasklet(secondTask)
                .build();
    }

    @Bean
    public Job secondJob(){
        return jobBuilderFactory.get("Second Job")
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep())
                .next(secondStep())
                .build();
    }

    public Step firstChunkStep(){
        return stepBuilderFactory.get("First Chunk Step")
                .<Integer,Long>chunk(3)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();
    }

    /*public Tasklet secondTaskLet(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
                System.out.println("Second Task");
                return RepeatStatus.FINISHED;
            }
        };
    }*/
}
