package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.service.LecturerService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UniqueLecturerCodeValidator implements Validator {
    private LecturerService lecturerService;

    public UniqueLecturerCodeValidator(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Lecturer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Lecturer lecturer = (Lecturer) target;

        if (lecturer.getId() == null
                && this.lecturerService.checkUniqueLecturerCode(lecturer.getCode())) {
            errors.rejectValue("code", "lecturer.add.code.existsMessage",
                    "Mã giảng viên đã tồn tại");
        }
    }
}
