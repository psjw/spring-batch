package io.springbatch.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SyncJobLauncherController {

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    @PostMapping(value = "/batch")
    public String launch(@RequestBody Member member) throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("id", member.getId())
                .addDate("date", new Date())
                .toJobParameters();

        jobLauncher.run(job, jobParameters);
        return "batch completed";
    }
}