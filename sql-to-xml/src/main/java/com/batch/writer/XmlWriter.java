package com.batch.writer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.batch.model.User;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class XmlWriter extends StaxEventItemWriter<User>{

	public XmlWriter()
	 {
		 log.info("xmlWriter() - Started");
		 this.setResource(new FileSystemResource("xml/user.xml"));
		 this.setMarshaller(userUnmarshaller());
		 this.setRootTagName("users");
		 log.info("XmlWriter() - Completed");
	 }
	
	 public XStreamMarshaller userUnmarshaller()
	 {
		 XStreamMarshaller unMarshaller = new XStreamMarshaller();
		 Map<String, Class> aliases = new HashMap<String, Class>();
		 aliases.put("user", User.class);
		 unMarshaller.setAliases(aliases);
		 return unMarshaller;
	 }
}
