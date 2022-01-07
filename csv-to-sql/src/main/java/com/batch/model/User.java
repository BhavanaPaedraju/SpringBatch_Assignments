package com.batch.model;

public class User {
    private String gender;
    private String race;
    private String parental_level_of_education;
    private String test_preparation_course;
    private long math_score;

    public User() {
    }

    public User(String gender, String race, String parental_level_of_education, String lunch, String test_preparation_course, long math_score, long reading_score, long writing_score) {
        this.gender = gender;
        this.race = race;
        this.parental_level_of_education = parental_level_of_education;
        this.test_preparation_course = test_preparation_course;
        this.math_score = math_score;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getParental_level_of_education() {
        return parental_level_of_education;
    }

    public void setParental_level_of_education(String parental_level_of_education) {
        this.parental_level_of_education = parental_level_of_education;
    }


    public String getTest_preparation_course() {
        return test_preparation_course;
    }

    public void setTest_preparation_course(String test_preparation_course) {
        this.test_preparation_course = test_preparation_course;
    }

    public long getMath_score() {
        return math_score;
    }

    public void setMath_score(long math_score) {
        this.math_score = math_score;
    }

}