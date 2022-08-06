package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.repository.ThesisRepository;
import com.buikhanhhuy.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisServiceImplement implements ThesisService {
    @Autowired
    private ThesisRepository thesisRepository;

    @Override
    public List<Thesis> getTheses() {
        return this.thesisRepository.getTheses();
    }

    @Override
    public boolean addThesis(Thesis thesis) {
        return this.thesisRepository.addThesis(thesis);
    }

    @Override
    public Thesis getThesisById(int thesisId) {
        return this.thesisRepository.getThesisById(thesisId);
    }
}
