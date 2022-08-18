package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Department;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DepartmentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Department.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Department department = (Department) target;
    }

}
