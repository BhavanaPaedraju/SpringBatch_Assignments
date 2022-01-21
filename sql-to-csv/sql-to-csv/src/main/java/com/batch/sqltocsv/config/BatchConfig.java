package com.batch.sqltocsv.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.RowMapper;

import com.batch.sqltocsv.model.User;
import com.batch.sqltocsv.processor.UserItemProcessor;
import com.batch.sqltocsv.reader.DbReader;
import com.batch.sqltocsv.reader.UserRowMapper;
import com.batch.sqltocsv.writer.CsvWriter;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;
    @Bean
    public DbReader reader() {
        return new DbReader(dataSource);
    }
   
    @Bean
    public UserItemProcessor processor(){
        return new UserItemProcessor();
    }

    @Bean
    public CsvWriter writer() {
        return new CsvWriter();
    }
   
    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
        		.<User,User> chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    @Bean
    public Job exportUserJob(){
        return jobBuilderFactory.get("exportUserJob")
        		.incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }
}


