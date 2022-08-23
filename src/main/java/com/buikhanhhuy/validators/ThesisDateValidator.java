package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Thesis;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

public class ThesisDateValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Thesis.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Thesis thesis = (Thesis) target;

        if (thesis.getStartDate() != null
                && thesis.getComplateDate() != null
                && thesis.getThesisStartDate() != null
                && thesis.getThesisEndDate() != null) {
            if(thesis.getStartDate().compareTo(new Date()) < 0){
                errors.rejectValue("startDate", "thesis.add.startDate.invalid",
                        "Ngày bắt đầu phải lớn hớn hôm nay");
            }

            if (thesis.getComplateDate().compareTo(thesis.getStartDate()) < 0) {
                errors.rejectValue("complateDate", "thesis.add.endDate.invalid",
                        "Ngày kết thúc phải lớn hơn ngày bắt đầu");
            }

            if(thesis.getThesisStartDate().compareTo(thesis.getStartDate()) < 0){
                errors.rejectValue("thesisStartDate", "thesis.add.thesisStartDate.invalid",
                        "Ngày bắt đầu nộp khóa luận phải lớn hơn ngày bắt đầu thực hiện khóa luận");
            }

            if(thesis.getThesisEndDate().compareTo(thesis.getThesisStartDate()) < 0){
                errors.rejectValue("thesisEndDate", "thesis.add.thesisEndDate.invalid",
                        "Ngày kết thúc nộp khóa luận phải lớn hơn ngày bắt đầu nộp khóa luận");
            }
        }
    }
}
