package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.SchoolYear;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SchoolYearValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SchoolYear.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SchoolYear schoolYear = (SchoolYear) target;
    }
}
