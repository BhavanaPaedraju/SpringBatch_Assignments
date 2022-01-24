package com.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.batch.model.Person;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class PersonProcessor implements ItemProcessor<Person,Person> {

	@Override
	public Person process(Person person) throws Exception {
		log.info("Processing...");
		return person;
	}

}
