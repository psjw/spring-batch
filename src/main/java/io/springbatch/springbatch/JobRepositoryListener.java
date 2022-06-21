package io.springbatch.springbatch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * packageName : io.springbatch.springbatch
 * fileName : JobRepositoryListener
 * author : psjw
 */
@Component
public class JobRepositoryListener implements JobExecutionListener {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        JobParameters jobParameters = new JobParametersBuilder().addString("requestDate", "20220621").toJobParameters();
        JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParameters);
        if (lastJobExecution != null) {
            for (StepExecution stepExecution : lastJobExecution.getStepExecutions()) {
                BatchStatus status = stepExecution.getStatus();
                System.out.println("status : " + status);

                ExitStatus exitStatus = jobExecution.getExitStatus();
                System.out.println("exitStatus : " + exitStatus);

                String stepName = stepExecution.getStepName();
                System.out.println("stepName : " + stepName);
            }
        }
    }
}
