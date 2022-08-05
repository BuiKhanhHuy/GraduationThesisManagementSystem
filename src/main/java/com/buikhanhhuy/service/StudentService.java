package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    public List<Student> getStudents( Map<String, String> params);
    public long countStudent(Map<String, String> params);
    public Student getStudentById(int studentId);
    public boolean addStudent(Student student);
    public boolean updateStudent(int studentId, Student student);
    public boolean deleteStudent(int studentId);
}
