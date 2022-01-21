package com.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.batch.model.Person;

@Component
public class PersonProcessor implements ItemProcessor<Person,Person> {

	@Override
	public Person process(Person person) throws Exception {
		System.out.println("Processing person with id: "+person.getId()+" and their details are: "+person);
		return person;
	}

}
