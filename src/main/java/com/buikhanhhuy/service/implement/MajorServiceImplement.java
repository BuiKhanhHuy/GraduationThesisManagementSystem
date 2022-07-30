package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Major;
import com.buikhanhhuy.pojo.News;
import com.buikhanhhuy.repository.MajorRepository;
import com.buikhanhhuy.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImplement implements MajorService {
    @Autowired
    private MajorRepository majorRepository;

    @Override
    public List<Major> getMajors() {
        return this.majorRepository.getMajors();
    }

    @Override
    public boolean addMajor(Major major) {
        return this.majorRepository.addMajor(major);
    }

    @Override
    public Major getMajorById(int majorId) {
        return this.majorRepository.getMajorById(majorId);
    }

    @Override
    public boolean updateMajor(int majorId, Major major) {
        return this.majorRepository.updateMajor(majorId, major);
    }

    @Override
    public boolean deleteMajor(int majorId) {
        return this.majorRepository.deleteMajor(majorId);
    }
}
