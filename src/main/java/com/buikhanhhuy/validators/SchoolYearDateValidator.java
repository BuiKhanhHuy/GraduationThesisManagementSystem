package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.SchoolYear;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SchoolYearDateValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SchoolYear.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SchoolYear schoolYear = (SchoolYear) target;

        if (schoolYear.getStartDate() != null && schoolYear.getEndDate() != null) {
            if (schoolYear.getEndDate().compareTo(schoolYear.getStartDate()) < 0) {
                errors.rejectValue("endDate",
                        "schoolYear.add.endDate.afterStartDateMessage",
                        "Ngày kết thúc phải lớn hơn ngày bắt đầu");
            }
        }

    }
}
