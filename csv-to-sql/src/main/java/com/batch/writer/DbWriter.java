package com.batch.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import com.batch.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DbWriter extends JdbcBatchItemWriter<User>{
	
	 public DbWriter(DataSource dataSource) {
		 log.info("Writer Class Started");
		 this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
	     this.setSql("insert into user(gender,race,parental_level_of_education,test_preparation_course,math_score) values (:gender,:race,:parental_level_of_education,:test_preparation_course,:math_score)");
	     this.setDataSource(dataSource);
	     log.info("Writer Class Completed");
	 }

}
