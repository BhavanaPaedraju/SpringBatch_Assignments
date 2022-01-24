package com.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batch.model.Person;
import com.batch.processor.PersonProcessor;
import com.batch.reader.DbReader;
import com.batch.writer.TxtWriter;
import javax.sql.DataSource;

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
    public PersonProcessor processor(){
        return new PersonProcessor();
    }

    @Bean
    public TxtWriter writer() {
        return new TxtWriter();
    }
   
    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
        		.<Person,Person> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    @Bean
    public Job exportPersonJob(){
        return jobBuilderFactory.get("exportPersonJob")
        		.incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }
}


