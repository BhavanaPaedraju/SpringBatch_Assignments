package com.batch.sqltocsv.reader;

import org.springframework.batch.item.database.JdbcCursorItemReader;

import com.batch.sqltocsv.model.User;

import javax.sql.DataSource;
public class DbReader extends JdbcCursorItemReader<User>{

    public DbReader(DataSource dataSource){
        this.setDataSource(dataSource);
        this.setSql("SELECT gender,race,parental_level_of_education,test_preparation_course,math_score FROM user WHERE race='group B';");
        this.setRowMapper(new UserRowMapper());
    }
}