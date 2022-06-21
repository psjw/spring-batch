package io.springbatch.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * packageName : io.springbatch.springbatch
 * fileName : CustomTasklet
 * author : psjw
 */

//@Component
//Bean 등록 후 다른 객체를 주입받아서 사용가능
public class CustomTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("#####CustomTasklet####");
        return RepeatStatus.FINISHED;
    }
}
