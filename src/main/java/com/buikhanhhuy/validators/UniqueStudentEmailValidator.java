package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.service.StudentService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UniqueStudentEmailValidator implements Validator {
    private final StudentService studentService;

    public UniqueStudentEmailValidator(StudentService studentService){
        this.studentService = studentService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;

        if(student.getId() == null
                && this.studentService.checkUniqueStudentEmail(student.getEmail())){
            errors.rejectValue("email", "student.add.email.existsMessage",
                    "Email sinh viên đã tồn tại");
        }
    }
}
