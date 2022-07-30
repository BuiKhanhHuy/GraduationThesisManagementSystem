package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.SchoolYear;

import java.util.List;

public interface SchoolYearRepository {
    public List<SchoolYear> getSchoolYears ();
    public boolean addSchoolYear(SchoolYear schoolYear);
    public SchoolYear getSchoolYearById(int schoolYearId);
    public boolean updateSchoolYear (int schoolYearId, SchoolYear schoolYear);
    public boolean deleteSchoolYear (int schoolYearId);
}
