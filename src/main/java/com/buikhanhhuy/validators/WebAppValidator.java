package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.pojo.EvaluationMethod;
import com.buikhanhhuy.pojo.SchoolYear;
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
                || SchoolYear.class.isAssignableFrom(clazz)
                || EvaluationMethod.class.isAssignableFrom(clazz);
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
