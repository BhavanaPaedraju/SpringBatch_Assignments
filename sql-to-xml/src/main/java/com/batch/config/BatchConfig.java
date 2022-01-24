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
import com.batch.processor.UserProcessor;
import com.batch.reader.DbReader;
import com.batch.writer.XmlWriter;

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
	public DbReader reader(){
		return new DbReader(dataSource);
	}
	
	@Bean
	public UserProcessor processor(){
		return new UserProcessor();
	}
	
	@Bean
	public XmlWriter writer() throws Exception{
		return new XmlWriter();
	}
	
	@Bean
	public Step step1() throws Exception{
		return stepBuilderFactory
				.get("step1")
				.<User,User>chunk(100)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}

	@Bean
	public Job exportUserJob() throws Exception{
		return jobBuilderFactory
				.get("exportUserJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
	}
}