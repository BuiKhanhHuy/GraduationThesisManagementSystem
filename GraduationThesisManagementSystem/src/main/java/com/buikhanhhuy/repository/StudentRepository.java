package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StudentRepository {
    public boolean checkUniqueStudentCode(String studentCode);
    public boolean checkUniqueStudentEmail(String studentEmail);
    public boolean checkUniqueStudentPhone(String studentPhone);
    public List<Object[]> getStudentOptions();
    public List<Student> getStudents (Map<String, String> params);
    public long countStudent(Map<String, String> params);
    public long countAllStudent();
    public Student getStudentById(int studentId);
    public boolean addStudent(Student student);
    public boolean updateStudent(int studentId, Student student);
    public boolean deleteStudent(int studentId);;
}
