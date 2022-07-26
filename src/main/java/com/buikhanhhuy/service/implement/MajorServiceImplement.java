package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Major;
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
}
