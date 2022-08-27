package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.service.StudentService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UniqueStudentCodeValidator implements Validator {
    private final StudentService studentService;

    public UniqueStudentCodeValidator(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;

        if (student.getId() == null
                && this.studentService.checkUniqueStudentCode(student.getCode())) {
            errors.rejectValue("code", "student.add.code.existsMessage",
                    "Mã sinh viên đã tồn tại");
        }
    }
}
