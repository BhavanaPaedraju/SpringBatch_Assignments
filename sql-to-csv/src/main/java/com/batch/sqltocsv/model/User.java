package com.batch.sqltocsv.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class User {
    private @Getter @Setter String gender;
    private @Getter @Setter String race;
    private @Getter @Setter String parental_level_of_education;
    private @Getter @Setter String test_preparation_course;
    private @Getter @Setter long math_score;

}