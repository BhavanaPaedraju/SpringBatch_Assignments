package com.batch.sqltocsv.processor;

import org.springframework.batch.item.ItemProcessor;

import com.batch.sqltocsv.model.User;

public class UserItemProcessor implements ItemProcessor<User,User> {

    @Override
    public User process(User user) throws Exception {
        return user;
    }
}