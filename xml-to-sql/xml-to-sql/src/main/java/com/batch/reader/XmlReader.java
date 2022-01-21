package com.batch.reader;

import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.batch.model.User;

public class XmlReader extends StaxEventItemReader<User> {
	

	public XmlReader () {

		this.setResource(new ClassPathResource("User.xml"));
		this.setFragmentRootElementName("user");
		Jaxb2Marshaller jaxb2Marshaller=new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(User.class);
		this.setUnmarshaller(jaxb2Marshaller);

	}

}