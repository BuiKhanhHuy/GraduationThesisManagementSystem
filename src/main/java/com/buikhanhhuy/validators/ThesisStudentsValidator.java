package com.buikhanhhuy.validators;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.service.ThesisService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ThesisStudentsValidator implements Validator {
    private final ThesisService thesisService;

    public ThesisStudentsValidator(ThesisService thesisService) {
        this.thesisService = thesisService;
    }
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
        }else{
            for (Student student : thesis.getStudents()) {
                if (this.thesisService
                        .checkStudentDoManyThesisInASchoolYear(student.getId())) {
                    errors.rejectValue("students", "thesis.add.students.doManyThesisInASchoolYearMessage",
                            "Đã tồn tại sinh viên đang thực hiện khóa luận tốt nghiệp");
                    break;
                }

                if(this.thesisService
                        .checkStudentCompletedThesis(student.getId())){
                    errors.rejectValue("students", "thesis.add.students.studentCompletedThesis",
                            "Đã tồn tại sinh viên đã hoàn thành khóa luận tốt nghiệp");
                    break;
                }
            }
        }
    }
}
