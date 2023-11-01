package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReadExcelService {
    public List<Student> getStudentsFromFile(MultipartFile file);
    public List<Lecturer> getLecturersFromFile(MultipartFile file);
}
