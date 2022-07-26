package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.repository.LecturerRepository;
import com.buikhanhhuy.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerServiceImplement implements LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;

    @Override
    public List<Lecturer> getLecturers() {
        return this.lecturerRepository.getLecturers();
    }
}
