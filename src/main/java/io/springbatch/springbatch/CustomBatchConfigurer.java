package io.springbatch.springbatch;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * packageName : io.springbatch.springbatch
 * fileName : CustomBatchConfigurer
 * author : psjw
 */
@Configuration
public class CustomBatchConfigurer extends BasicBatchConfigurer {
    private final DataSource dataSource;

    protected CustomBatchConfigurer(BatchProperties properties, DataSource dataSource, TransactionManagerCustomizers transactionManagerCustomizers) {
        super(properties, dataSource, transactionManagerCustomizers);
        this.dataSource = dataSource;
    }

    @Override
    protected JobRepository createJobRepository() throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(getTransactionManager());
        jobRepositoryFactoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        jobRepositoryFactoryBean.setTablePrefix("SYSTEM_"); //Table 명도 변경해줘야됨
        return jobRepositoryFactoryBean.getObject();
    }
}
