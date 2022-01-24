package com.batch.sqltocsv.processor;

import org.springframework.batch.item.ItemProcessor;

import com.batch.sqltocsv.model.User;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class UserItemProcessor implements ItemProcessor<User,User> {

    @Override
    public User process(User user) throws Exception {
    	log.info("Processing....");
        return user;
    }
}