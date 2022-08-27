package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.Council;
import com.buikhanhhuy.pojo.Thesis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ThesisRepository {
    public boolean checkStudentDoManyThesisInASchoolYear(int studentId);
    public boolean checkStudentCompletedThesis(int studentId);
    public List<Object[]> getThesisOptions(String isCouncil);
    public List<Thesis> getTheses(Map<String, String> params);
    public long countThesis(Map<String, String> params);
    public long countAllThesis();
    public Boolean addThesis(Thesis thesis);

    public Thesis getThesisById(int thesisId);

    public boolean deleteThesis(int thesisId);

    public Double scoreOfAThesisInCouncil(int councilId, int thesisId);
    public Council thesisResult (int councilId);
    public boolean uploadThesisReportFile(int thesisId, String urlReportFile);
}
