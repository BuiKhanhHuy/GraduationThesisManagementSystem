package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.SchoolYear;
import com.buikhanhhuy.service.SchoolYearService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UniqueSchoolYearNameValidator implements Validator {
    private final SchoolYearService schoolYearService;

    public UniqueSchoolYearNameValidator(SchoolYearService schoolYearService) {
        this.schoolYearService = schoolYearService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SchoolYear.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SchoolYear schoolYear = (SchoolYear) target;

        if (schoolYear.getId() == null
            && this.schoolYearService.checkUniqueSchoolYearName(schoolYear.getName())) {
            errors.rejectValue("name", "schoolYear.add.name.existsMessage",
                    "Niên khóa đã tồn tại");
        }
    }
}
