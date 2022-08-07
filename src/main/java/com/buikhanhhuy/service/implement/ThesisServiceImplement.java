package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.repository.ThesisRepository;
import com.buikhanhhuy.service.EmailService;
import com.buikhanhhuy.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisServiceImplement implements ThesisService {
    @Autowired
    private ThesisRepository thesisRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public void sendReviewLectureThesisNotification(Thesis thesis) {
    }

    @Override
    public List<Thesis> getTheses() {
        return this.thesisRepository.getTheses();
    }

    @Override
    public boolean addThesis(Thesis thesis) {
        if (this.thesisRepository.addThesis(thesis)) {
            this.sendReviewLectureThesisNotification(thesis);
            return true;
        }

        return false;
    }

    @Override
    public Thesis getThesisById(int thesisId) {
        return this.thesisRepository.getThesisById(thesisId);
    }


}
