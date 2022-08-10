package com.buikhanhhuy.formatters;

import com.buikhanhhuy.pojo.Student;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

public class StudentFormatter implements Formatter<Student> {
    @Override
    public Student parse(String studentId, Locale locale) throws ParseException {
        Student student = new Student();
        student.setId(Integer.parseInt(studentId));

        return student;
    }

    @Override
    public String print(Student student, Locale locale) {
        return Objects.toString(student.getId());
    }
}
