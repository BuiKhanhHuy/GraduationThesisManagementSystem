package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.SchoolYear;

import java.util.List;
import java.util.Map;

public interface SchoolYearService {
    public boolean checkUniqueSchoolYearName (String schoolYearName);
    public List<SchoolYear> getSchoolYears(Map<String, String> params);
    public List<Object[]> getSchoolYearOptions();
    public long countSchoolYear(Map<String, String> params);
    public boolean addSchoolYear(SchoolYear schoolYear);
    public SchoolYear getSchoolYearById(int schoolYearId);
    public boolean updateSchoolYear(int schoolYearId, SchoolYear schoolYear);
    public boolean deleteSchoolYear(int schoolYearId);
}
