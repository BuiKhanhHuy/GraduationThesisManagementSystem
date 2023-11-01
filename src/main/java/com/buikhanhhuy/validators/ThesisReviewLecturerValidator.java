package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.Thesis;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

public class ThesisReviewLecturerValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Thesis.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Thesis thesis = (Thesis) target;

       if(thesis.getLecturers().contains(thesis.getReviewLecturer())){
           errors.rejectValue("reviewLecturer",
                   "thesis.add.reviewLecturer.uniqueWithLecturers",
                   "Giảng viên phản biện phải khác giảng viên hướng dẫn");
       }
    }
}
