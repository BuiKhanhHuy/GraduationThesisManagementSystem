package com.buikhanhhuy.validators;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Thesis;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ThesisStudentPerformValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Thesis.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Thesis thesis = (Thesis) target;

        if(thesis.getPerformStudentsId().size() > SystemConstant.MAXIMUM_NUMBER_OF_STUDENTS_PERFORMED){
            errors.rejectValue("performStudentsId",
                    "thesis.add.performStudentsId.quantityMessage",
                    "Số lượng sinh viên thực hiện không được lớn hơn 2 sinh viên");
        }
    }
}
