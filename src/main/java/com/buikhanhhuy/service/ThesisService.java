package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Thesis;

import java.util.List;

public interface ThesisService {
    public List<Thesis> getTheses();

    public void sendReviewLectureThesisNotification(Thesis thesis);
    public boolean addThesis(Thesis thesis);

    public Thesis getThesisById(int thesisId);

}
