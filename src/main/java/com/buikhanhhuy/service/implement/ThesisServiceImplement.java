package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.repository.ThesisRepository;
import com.buikhanhhuy.service.EmailService;
import com.buikhanhhuy.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ThesisServiceImplement implements ThesisService {
    @Autowired
    private ThesisRepository thesisRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public void sendReviewLectureThesisNotification(Thesis thesis) {
        String[] toEmail;
        Map<String, Object> model = new HashMap<>();
        Thesis modelThesis = this.thesisRepository.getThesisById(thesis.getId());

        model.put("thesis", modelThesis);

        toEmail = new String[]{modelThesis.getReviewLecturer().getEmail()};

        this.emailService.sendMail("Thông báo giảng viên phản biện khóa luận tốt nghiệp", toEmail, model, SystemConstant.REVIEW_LECTURER_EMAIL_TEMPLATE);
    }

    @Override
    public List<Object[]> getThesisOptions() {
        return this.thesisRepository.getThesisOptions();
    }

    @Override
    public List<Thesis> getTheses(Map<String, String> params) {
        return this.thesisRepository.getTheses(params);
    }

    @Override
    public long countThesis(Map<String, String> params) {
        return this.thesisRepository.countThesis(params);
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

    @Override
    public boolean deleteThesis(int thesisId) {
        return this.thesisRepository.deleteThesis(thesisId);
    }


}
