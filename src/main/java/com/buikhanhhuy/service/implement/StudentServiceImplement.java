package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.repository.StudentRepository;
import com.buikhanhhuy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImplement implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getStudents(Map<String, String> params) {
        return this.studentRepository.getStudents(params);
    }

    @Override
    public Student getStudentById(int studentId) {
        return this.studentRepository.getStudentById(studentId);
    }

    @Override
    public boolean addStudent(Student student) {
        return this.studentRepository.addStudent(student);
    }

    @Override
    public boolean updateStudent(int studentId, Student student) {
        return this.studentRepository.updateStudent(studentId, student);
    }

    @Override
    public boolean deleteStudent(int studentId) {
        return this.studentRepository.deleteStudent(studentId);
    }
}
