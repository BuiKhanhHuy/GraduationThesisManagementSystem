package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.SchoolYear;
import com.buikhanhhuy.repository.SchoolYearRepository;
import com.buikhanhhuy.service.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SchoolYearServiceImplement implements SchoolYearService {
    @Autowired
    private SchoolYearRepository schoolYearRepository;

    @Override
    public boolean checkUniqueSchoolYearName(String schoolYearName) {
        return this.schoolYearRepository.checkUniqueSchoolYearName(schoolYearName);
    }

    @Override
    public List<SchoolYear> getSchoolYears(Map<String, String> params) {
        return this.schoolYearRepository.getSchoolYears(params);
    }

    @Override
    public List<Object[]> getSchoolYearOptions() {
        return this.schoolYearRepository.getSchoolYearOptions();
    }

    @Override
    public long countSchoolYear(Map<String, String> params) {
        return this.schoolYearRepository.countSchoolYear(params);
    }

    @Override
    public boolean addSchoolYear(SchoolYear schoolYear) {
        return this.schoolYearRepository.addSchoolYear(schoolYear);
    }

    @Override
    public SchoolYear getSchoolYearById(int schoolYearId) {
        return this.schoolYearRepository.getSchoolYearById(schoolYearId);
    }

    @Override
    public boolean updateSchoolYear(int schoolYearId, SchoolYear schoolYear) {
        return this.schoolYearRepository.updateSchoolYear(schoolYearId, schoolYear);
    }

    @Override
    public boolean deleteSchoolYear(int schoolYearId) {
        return this.schoolYearRepository.deleteSchoolYear(schoolYearId);
    }
}
