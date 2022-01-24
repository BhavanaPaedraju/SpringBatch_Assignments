package com.batch.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.ClassPathResource;

import com.batch.model.Person;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TxtWriter extends FlatFileItemWriter<Person>{
    public TxtWriter() {
    	log.info("TxtReader() - Started");
        this.setResource(new ClassPathResource("person.txt"));
        DelimitedLineAggregator<Person> lineAggregator=new DelimitedLineAggregator<Person>();
        lineAggregator.setDelimiter(",");
        BeanWrapperFieldExtractor<Person> fieldExtractor=new BeanWrapperFieldExtractor<Person>();
        fieldExtractor.setNames(new String[]{"id","name"});
        lineAggregator.setFieldExtractor(fieldExtractor);
        this.setLineAggregator(lineAggregator);
        log.info("TxtReader() - Completed");
    }
}