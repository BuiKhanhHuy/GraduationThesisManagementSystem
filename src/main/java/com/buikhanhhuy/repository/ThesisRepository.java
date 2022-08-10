package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.Thesis;

import java.util.List;
import java.util.Map;

public interface ThesisRepository {
    public List<Object[]> getThesisOptions ();
    public List<Thesis> getTheses(Map<String, String> params);
    public long countThesis(Map<String, String> params);
    public boolean addThesis(Thesis thesis);
    public Thesis getThesisById(int thesisId);
    public boolean deleteThesis(int thesisId);
}
