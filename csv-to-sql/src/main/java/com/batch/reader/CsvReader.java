package com.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import com.batch.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvReader extends FlatFileItemReader<User>{
	
	public CsvReader() {
		log.info("Reader Class Started");
		this.setResource(new ClassPathResource("user.csv"));
        this.setName("CSV-Reader");
        this.setLinesToSkip(1);
        this.setLineMapper(getLineMapper());
        log.info("Reader Class Completed");
	}
	
	private LineMapper<User> getLineMapper() {

        DefaultLineMapper<User>lineMapper= new DefaultLineMapper<> ();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"gender","race","parental_level_of_education","test_preparation_course","math_score"});
        lineTokenizer.setIncludedFields(new int[]{0,1,2,4,5});

        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

}