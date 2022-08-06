package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.Thesis;

import java.util.List;

public interface ThesisRepository {

    public List<Thesis> getTheses();
    public boolean addThesis(Thesis thesis);
    public Thesis getThesisById(int thesisId);
}
