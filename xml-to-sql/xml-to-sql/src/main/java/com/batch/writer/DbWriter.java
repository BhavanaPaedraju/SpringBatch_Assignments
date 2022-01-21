package com.batch.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import com.batch.model.User;

public class DbWriter extends JdbcBatchItemWriter<User> {

	public DbWriter (DataSource dataSource) {
		this.setDataSource(dataSource);
		this.setSql("INSERT INTO User(userId,firstName,lastName,email,age) values(:userId,:firstName,:lastName,:email,:age)");
		this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
		this.afterPropertiesSet();
	}

}