package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.Council;

import java.util.List;
import java.util.Map;

public interface CouncilRepository {
    public List<Council> getCouncils(Map<String, String> params);
    public boolean addCouncil(Council council);
    public boolean deleteCouncil(int councilId);
}
