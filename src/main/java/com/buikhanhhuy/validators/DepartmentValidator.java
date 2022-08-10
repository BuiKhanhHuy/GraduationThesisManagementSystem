package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.annotation.RequestScope;

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
