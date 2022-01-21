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

import com.batch.model.User;
import com.batch.processor.UserItemProcessor;
import com.batch.reader.XmlReader;
import com.batch.writer.DbWriter;


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
	public XmlReader readDataFromXml() {
		
		return new XmlReader();
	}

	@Bean
	public UserItemProcessor processor() {
		return new UserItemProcessor();
	}

	@Bean
	public DbWriter writeDataInDB() {
		return new DbWriter(dataSource);
	}

	@Bean
	public Step executeUserStep() {
		return stepBuilderFactory.get("executeUserStep")
				.<User, User>chunk(10)
				.reader(readDataFromXml())
				.processor(processor())
				.writer(writeDataInDB())
				.build();
	}
	@Bean
	public Job processUserJob() {
		return jobBuilderFactory.get("processUserJob")
				.incrementer(new RunIdIncrementer())
				.flow(executeUserStep())
				.end()
				.build();
	}
}