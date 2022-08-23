package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.service.LecturerService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UniqueLecturerPhoneValidator implements Validator {
    private LecturerService lecturerService;

    public UniqueLecturerPhoneValidator(LecturerService lecturerService){
        this.lecturerService = lecturerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Lecturer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Lecturer lecturer = (Lecturer) target;

        if(lecturer.getId() == null
                && this.lecturerService.checkUniqueLecturerPhone(lecturer.getPhone())){
            errors.rejectValue("phone", "lecturer.add.phone.existsMessage",
                    "Số điện thoại giảng viên đã tồn tại");
        }
    }
}
