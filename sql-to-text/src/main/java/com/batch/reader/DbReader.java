package com.batch.reader;

import org.springframework.batch.item.database.JdbcCursorItemReader;

import com.batch.mapper.PersonRowMapper;
import com.batch.model.Person;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
@Slf4j
public class DbReader extends JdbcCursorItemReader<Person>{

    public DbReader(DataSource dataSource){
    	log.info("DbReader() - Started");
        this.setDataSource(dataSource);
        this.setSql("SELECT id,name FROM person WHERE name='Swetha';");
        this.setRowMapper(new PersonRowMapper());
        log.info("DbReader() - Completed");
    }
}