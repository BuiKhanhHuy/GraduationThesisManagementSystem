package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.Major;

import java.util.List;
import java.util.Map;

public interface MajorRepository {
    public boolean checkUniqueMajorCode(String majorCode);
    public boolean checkUniqueMajorName(String majorName);
    public List<Major> getMajors(Map<String, String> params);
    public long countMajor(Map<String, String> params);
    public List<Object[]> getMajorOptions();
    public boolean addMajor(Major major);
    public Major getMajorById(int majorId);
    public boolean updateMajor(int majorId, Major major);
    public boolean deleteMajor (int majorId);
}
