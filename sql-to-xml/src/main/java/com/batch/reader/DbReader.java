package com.batch.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;

import com.batch.mapper.UserRowMapper;
import com.batch.model.User;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DbReader extends JdbcCursorItemReader<User>{

    public DbReader(DataSource dataSource){
    	log.info("DbReader() - Started");
        this.setDataSource(dataSource);
        this.setSql("SELECT * FROM user;");
        this.setRowMapper(new UserRowMapper());
        log.info("DbReader() - Completed");
    }
}