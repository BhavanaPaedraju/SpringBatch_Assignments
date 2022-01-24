package com.batch.processor;

import com.batch.model.User;
import org.springframework.batch.item.ItemProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserProcessor implements ItemProcessor<User,User> {

    @Override
    public User process(User user) throws Exception {
    	log.info("Processing...");
        return user;
    }
}