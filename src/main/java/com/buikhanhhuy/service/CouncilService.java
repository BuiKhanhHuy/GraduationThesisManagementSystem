package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Council;

import java.util.List;
import java.util.Map;

public interface CouncilService {
    public List<Council> getCouncils(Map<String, String> params);
    public boolean addCouncil(Council council);
    public boolean deleteCouncil(int councilId);
}
