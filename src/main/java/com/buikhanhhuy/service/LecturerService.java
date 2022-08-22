package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Lecturer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface LecturerService {
    public boolean checkUniqueLecturerCode(String lecturerCode);
    public boolean checkUniqueLecturerEmail(String lecturerEmail);
    public boolean checkUniqueLecturerPhone(String lecturerPhone);
    public List<Object[]> getLecturerOptions();
    public List<Lecturer> getLecturers(Map<String, String> params);
    public long countLecturer(Map<String, String> params);
    public long countAllLecturer();
    public Lecturer getLecturerById(int lecturerId);
    public boolean addLecturer(Lecturer lecturer, MultipartFile file);
    public boolean updateLecturer(int lecturerId, Lecturer lecturer, MultipartFile file);
    public boolean deleteLecturer(int lecturerId);
}
