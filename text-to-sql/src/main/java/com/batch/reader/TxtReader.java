package com.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.batch.model.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j	
public class TxtReader extends FlatFileItemReader<Person> {

	public TxtReader () {
		log.info("Reader Class Started");
		this.setResource(new ClassPathResource("person.txt"));
	    this.setName("Text-Reader");
	    this.setLinesToSkip(1);
	    this.setLineMapper(lineMapper());
	    log.info("Reader Class Completed");
	}
	
	@Bean
    public LineMapper<Person> lineMapper() {

        DefaultLineMapper<Person> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "name");

        BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Person.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

}


