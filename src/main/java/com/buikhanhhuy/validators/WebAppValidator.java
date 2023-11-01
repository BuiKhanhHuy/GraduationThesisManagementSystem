package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.*;
import com.buikhanhhuy.req.PasswordUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Component
public class WebAppValidator implements Validator {
    @Autowired
    private javax.validation.Validator beanValidator;
    private Set<Validator> validators;

    @Override
    public boolean supports(Class<?> clazz) {
        return Department.class.isAssignableFrom(clazz)
                || Major.class.isAssignableFrom(clazz)
                || SchoolYear.class.isAssignableFrom(clazz)
                || Position.class.isAssignableFrom(clazz)
                || Lecturer.class.isAssignableFrom(clazz)
                || Student.class.isAssignableFrom(clazz)
                || User.class.isAssignableFrom(clazz)
                || Manage.class.isAssignableFrom(clazz)
                || EvaluationMethod.class.isAssignableFrom(clazz)
                || Topic.class.isAssignableFrom(clazz)
                || Thesis.class.isAssignableFrom(clazz)
                || Council.class.isAssignableFrom(clazz)
                || PasswordUser.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        for (Validator validator : validators) {
            validator.validate(target, errors);
        }

        Set<ConstraintViolation<Object>> constraintViolations = beanValidator.validate(target);
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            errors.rejectValue(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessageTemplate(), constraintViolation.getMessage());
        }
    }

    public void setValidators(Set<Validator> validators) {
        this.validators = validators;
    }
}
