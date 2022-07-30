package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Major;

import java.util.List;

public interface MajorService {

    public List<Major> getMajors();
    public boolean addMajor(Major major);
    public Major getMajorById(int majorId);
    public boolean updateMajor(int majorId, Major major);
    public boolean deleteMajor(int majorId);
}
