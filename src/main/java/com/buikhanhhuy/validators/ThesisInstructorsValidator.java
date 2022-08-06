package com.buikhanhhuy.validators;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Thesis;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ThesisInstructorsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Thesis.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Thesis thesis = (Thesis) target;
        if (thesis.getInstructorsId().size() > SystemConstant.MAX_NUMBER_OF_TEACHER_INSTRUCTIONS)
            errors.rejectValue("instructorsId", "thesis.add.instructorsId.quantityMessage", "Số lượng giảng viên hướng dẫn không được lớn hơn 2 giảng viên");
    }
}
