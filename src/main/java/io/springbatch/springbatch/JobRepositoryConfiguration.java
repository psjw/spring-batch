package io.springbatch.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName : io.springbatch.springbatch
 * fileName : JobRepositoryConfiguration
 * author : psjw
 */
@Configuration
@RequiredArgsConstructor
public class JobRepositoryConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    //--name=batchJob requestDate=20220621
    private final JobExecutionListener jobExecutionListener;

    @Bean
    public Job batchJob() {
        return jobBuilderFactory.get("batchJob")
                .start(step1())
                .next(step2())
                .listener(jobExecutionListener)
                .build();
    }


    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> null)
                .build();
    }
}
