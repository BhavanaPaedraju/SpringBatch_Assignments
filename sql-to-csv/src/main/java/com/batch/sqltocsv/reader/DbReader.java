package com.batch.sqltocsv.reader;
import org.springframework.batch.item.database.JdbcCursorItemReader;

import com.batch.sqltocsv.mapper.UserRowMapper;
import com.batch.sqltocsv.model.User;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Slf4j
public class DbReader extends JdbcCursorItemReader<User>{

    public DbReader(DataSource dataSource){
    	log.info("DbReader() - Started");
        this.setDataSource(dataSource);
        this.setSql("SELECT gender,race,parental_level_of_education,test_preparation_course,math_score FROM user WHERE race='group B';");
        this.setRowMapper(new UserRowMapper());
        log.info("DbReader() - Completed");
    }
}