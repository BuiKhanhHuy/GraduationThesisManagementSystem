package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.SchoolYear;
import com.buikhanhhuy.repository.SchoolYearRepository;
import com.buikhanhhuy.service.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolYearServiceImplement implements SchoolYearService {
    @Autowired
    private SchoolYearRepository schoolYearRepository;

    @Override
    public List<SchoolYear> getSchoolYears() {
        return this.schoolYearRepository.getSchoolYears();
    }
}
