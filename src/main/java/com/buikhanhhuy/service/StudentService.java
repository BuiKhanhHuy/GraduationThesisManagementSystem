package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface StudentService {
    public boolean checkUniqueStudentCode(String studentCode);
    public boolean checkUniqueStudentEmail(String studentEmail);
    public boolean checkUniqueStudentPhone(String studentPhone);
    public List<Object[]> getStudentOptions();
    public List<Student> getStudents( Map<String, String> params);
    public long countStudent(Map<String, String> params);
    public long countAllStudent();
    public Student getStudentById(int studentId);
    public boolean addStudent(Student student, MultipartFile file) throws IOException;
    public boolean updateStudent(int studentId, Student student, MultipartFile file) throws IOException;
    public boolean deleteStudent(int studentId);
}
