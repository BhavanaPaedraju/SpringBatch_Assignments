package com.batch.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import com.batch.model.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseWriter extends JdbcBatchItemWriter<Person> {

	public DatabaseWriter (DataSource dataSource) {
		log.info("Writer Class Started");
		this.setDataSource(dataSource);
		this.setSql("INSERT INTO person(id,name) values(:id,:name)");
		this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
		this.afterPropertiesSet();
		log.info("Writer Class Completed");
	}

}