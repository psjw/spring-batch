package io.springbatch.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName : io.springbatch.springbatch
 * fileName : StepContributionConfiguration
 * author : psjw
 */
@RequiredArgsConstructor
@Configuration
public class StepContributionConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob(){
        return jobBuilderFactory.get("job")
                .start(step1())
                .next(step2())
                .build();
    }


    private Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(((stepContribution, chunkContext) -> {
//                    stepContribution.getStepExecution().getJobExecution().getJobInstance().getJobName();
                    System.out.println("###step1###");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }
    private Step step2() {
        return stepBuilderFactory.get("step1")
                .tasklet(((stepContribution, chunkContext) -> {
                    System.out.println("###step1###");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }
}
