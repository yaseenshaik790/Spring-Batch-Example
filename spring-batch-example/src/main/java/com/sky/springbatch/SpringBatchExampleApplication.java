package com.sky.springbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableBatchProcessing
@EnableAsync
@ComponentScan({"com.sky.springbatch.config","com.sky.springbatch.service","com.sky.springbatch.listener",
"com.sky.springbatch.reader","com.sky.springbatch.processor","com.sky.springbatch.writer","com.sky.springbatch.controller"})
public class SpringBatchExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchExampleApplication.class, args);
    }

}
