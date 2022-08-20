package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.Council;

import java.util.List;
import java.util.Map;

public interface CouncilRepository {
    public List<Council> getCouncils(Map<String, String> params);
    public Council getCouncilById(int councilId);
    public long countCouncil(Map<String, String> params);
    public long countAllCouncil();
    public boolean addCouncil(Council council);
    public boolean deleteCouncil(int councilId);
    public List<Object[]> scoreDetailOfCouncilForThesis(int councilId, int thesisId);
    public Council lockOrUnlockCouncil(int councilId, boolean block);
}
