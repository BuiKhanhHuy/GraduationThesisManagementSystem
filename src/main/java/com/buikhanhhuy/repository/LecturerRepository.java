package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.Lecturer;

import java.util.List;
import java.util.Map;

public interface LecturerRepository {
    public boolean checkUniqueLecturerCode(String lecturerCode);
    public boolean checkUniqueLecturerEmail(String lecturerEmail);
    public boolean checkUniqueLecturerPhone(String lecturerPhone);
    public List<Object[]> getLecturerOptions ();
    public List<Lecturer> getLecturers(Map<String, String> params);
    public long countLecturer(Map<String, String> params);
    public long countAllLecturer();
    public Lecturer getLecturerById(int lecturerId);
    public boolean addLecturer(Lecturer lecturer);
    public boolean updateLecturer(int lecturerId, Lecturer lecturer);
    public boolean deleteLecturer(int lecturerId);
}
