package com.buikhanhhuy.validators;

import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.service.StudentService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UniqueStudentPhoneValidator implements Validator {
    private final StudentService studentService;

    public UniqueStudentPhoneValidator(StudentService studentService){
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
                && this.studentService.checkUniqueStudentPhone(student.getPhone())){
            errors.rejectValue("phone", "student.add.phone.existsMessage",
                    "Số điện thoại sinh viên đã tồn tại");
        }
    }
}
