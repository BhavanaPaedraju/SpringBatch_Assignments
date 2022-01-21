package com.batch.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import com.batch.model.Person;

public class DatabaseWriter extends JdbcBatchItemWriter<Person> {

	public DatabaseWriter (DataSource dataSource) {
		this.setDataSource(dataSource);
		this.setSql("INSERT INTO person(id,name) values(:id,:name)");
		this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
		this.afterPropertiesSet();
	}

}