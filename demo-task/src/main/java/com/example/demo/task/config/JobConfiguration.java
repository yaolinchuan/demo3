package com.example.demo.task.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liyuhong on 2017/7/7.
 */
@Configuration
@Slf4j
public class JobConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job1() {
        return jobBuilderFactory.get("job1")
                .start(stepBuilderFactory.get("job1step1")
                        .tasklet(new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                                log.info("Job1 was run");
                                return RepeatStatus.FINISHED;
                            }
                        })
                        .build())
                .build();
    }

    @Bean
    public Job job2() {
        return jobBuilderFactory.get("job2")
                .start(stepBuilderFactory.get("job2step1")
                        .tasklet(new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                                log.info("Job2 was run");
                                return RepeatStatus.FINISHED;
                            }
                        })
                        .build())
                .build();
    }
}
