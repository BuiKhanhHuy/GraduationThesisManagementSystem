package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Thesis;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ThesisService {
    public List<Object[]> getThesisOptions();
    public List<Thesis> getTheses(Map<String, String> params);
    public long countThesis(Map<String, String> params);
    public void sendReviewLectureThesisNotification(Thesis thesis);
    public boolean addThesis(Thesis thesis);
    public Thesis getThesisById(int thesisId);
    public boolean deleteThesis(int thesisId);
    public boolean uploadThesisReportFile(int thesisId, MultipartFile reportFile, String fileName);

}
