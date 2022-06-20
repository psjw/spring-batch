package io.springbatch.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * packageName : io.springbatch.springbatch
 * fileName : JobParameterConfiguration
 * author : psjw
 */


@Configuration
@RequiredArgsConstructor
public class JobParameterConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob(){
        return  jobBuilderFactory.get("Job")
                .start(step1())
                .next(step2())
                .build();
    }


    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(((stepContribution, chunkContext) -> {
                    JobParameters jobParameters = stepContribution.getStepExecution().getJobExecution().getJobParameters();
                    System.out.println("####step1###");
                    jobParameters.getString("name");
                    jobParameters.getLong("seq");
                    jobParameters.getDate("date");
                    jobParameters.getDouble("age");

                    Map<String, Object> jobParameters1 = chunkContext.getStepContext().getJobParameters();
                    return RepeatStatus.FINISHED;
                })).build();
    }
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(((stepContribution, chunkContext) -> {
                    System.out.println("####step2###");
                    return RepeatStatus.FINISHED;
                })).build();
    }
}
