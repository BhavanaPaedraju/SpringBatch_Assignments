package com.batch.sqltocsv.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.batch.sqltocsv.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user=new User();
        user.setGender(rs.getString("gender"));
        user.setRace(rs.getString("race"));
        user.setParental_level_of_education(rs.getString("parental_level_of_education"));
        user.setTest_preparation_course(rs.getString("test_preparation_course"));
        user.setMath_score(rs.getInt("math_score"));
        return user;
    }
}