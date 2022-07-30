package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.SchoolYear;

import java.util.List;

public interface SchoolYearService {
    public List<SchoolYear> getSchoolYears();
    public boolean addSchoolYear(SchoolYear schoolYear);
    public SchoolYear getSchoolYearById(int schoolYearId);
    public boolean updateSchoolYear(int schoolYearId, SchoolYear schoolYear);
    public boolean deleteSchoolYear(int schoolYearId);
}
