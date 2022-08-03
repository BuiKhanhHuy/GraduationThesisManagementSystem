package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Lecturer;

import java.util.List;
import java.util.Map;

public interface LecturerService {
    public List<Lecturer> getLecturers(Map<String, String> params);
    public long countLecturer(Map<String, String> params);
    public Lecturer getLecturerById(int lecturerId);
    public boolean addLecturer(Lecturer lecturer);
    public boolean updateLecturer(int lecturerId, Lecturer lecturer);
    public boolean deleteLecturer(int lecturerId);
}
