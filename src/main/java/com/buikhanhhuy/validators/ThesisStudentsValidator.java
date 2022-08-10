package com.buikhanhhuy.validators;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Thesis;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ThesisStudentsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Thesis.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Thesis thesis = (Thesis) target;

        if(thesis.getStudents().size() > SystemConstant.MAXIMUM_NUMBER_OF_STUDENTS_PERFORMED){
            errors.rejectValue("students",
                    "thesis.add.students.quantityMessage",
                    "Số lượng sinh viên thực hiện không được lớn hơn 2 sinh viên");
        }
    }
}
