package io.springbatch.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ASyncJobLauncherController {

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher simpleJobLauncher;

    @Autowired
    // class com.sun.proxy.$Proxy57의 실제객체를 가지고 있음
    private BasicBatchConfigurer basicBatchConfigurer;

    @PostMapping(value = "/asyncbatch")
    public String launch(@RequestBody Member member) throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("id", member.getId())
                .addDate("date", new Date())
                .toJobParameters();

        SimpleJobLauncher jobLauncher = (SimpleJobLauncher)basicBatchConfigurer.getJobLauncher();
        //오류발생 ->java.lang.ClassCastException: class com.sun.proxy.$Proxy57 cannot be cast to class org.springframework.batch.core.launch.support.SimpleJobLauncher
//        SimpleJobLauncher jobLauncher = (SimpleJobLauncher)simpleJobLauncher;
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.run(job, jobParameters);
        return "batch completed";
    }
}