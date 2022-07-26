package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.repository.StudentRepository;
import com.buikhanhhuy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImplement implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        return this.studentRepository.getStudents();
    }
}
