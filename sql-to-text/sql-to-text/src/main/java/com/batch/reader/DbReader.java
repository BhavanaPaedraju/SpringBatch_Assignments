package com.batch.reader;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import com.batch.model.Person;
import javax.sql.DataSource;

public class DbReader extends JdbcCursorItemReader<Person>{

    public DbReader(DataSource dataSource){
        this.setDataSource(dataSource);
        this.setSql("SELECT id,name FROM person WHERE name='Swetha';");
        this.setRowMapper(new PersonRowMapper());
    }
}