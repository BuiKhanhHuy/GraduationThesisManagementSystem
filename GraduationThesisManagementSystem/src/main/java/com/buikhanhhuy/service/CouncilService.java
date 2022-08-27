package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Council;

import java.util.List;
import java.util.Map;

public interface CouncilService {
    public List<Council> getCouncils(Map<String, String> params);
    public Council getCouncilById(int councilId);
    public long countCouncil(Map<String, String> params);
    public long countAllCouncil();
    public boolean addCouncil(Council council);
    public boolean deleteCouncil(int councilId);
    public List<Object[]> scoreDetailOfCouncilForThesis(int councilId, int thesisId);
    public void sendThesisResultNotification(Council council);
    public boolean lockOrUnlockCouncil(int councilId, boolean block);
}
