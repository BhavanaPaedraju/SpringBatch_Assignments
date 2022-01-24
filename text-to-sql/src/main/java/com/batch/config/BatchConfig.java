package com.batch.config;

import javax.sql.DataSource;

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
import com.batch.reader.TxtReader;
import com.batch.writer.DatabaseWriter;


@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;
	
	@Bean
	public TxtReader itemReader() {
		return new TxtReader();
	}
	
	@Bean
	public PersonProcessor processor() {
		return new PersonProcessor();
	}
	
	@Bean
	public DatabaseWriter itemWriter() {
		return new DatabaseWriter(dataSource);
	}
	
	@Bean
	public Step executePersonStep() {
		return stepBuilderFactory.get("executePersonrStep")
				.<Person,Person>chunk(10)
				.reader(itemReader())
				.processor(processor())
				.writer(itemWriter())
				.build();
	}
	@Bean
	public Job processPersonJob() {
		return jobBuilderFactory.get("processPersonJob")
				.incrementer(new RunIdIncrementer())
				.flow(executePersonStep())
				.end()
				.build();
	}

}
