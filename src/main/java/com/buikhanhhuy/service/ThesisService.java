package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Thesis;

import java.util.List;
import java.util.Map;

public interface ThesisService {
    public List<Object[]> getThesisOptions();
    public List<Thesis> getTheses(Map<String, String> params);
    public long countThesis(Map<String, String> params);
    public void sendReviewLectureThesisNotification(Thesis thesis);
    public boolean addThesis(Thesis thesis);
    public Thesis getThesisById(int thesisId);
    public boolean deleteThesis(int thesisId);

}
