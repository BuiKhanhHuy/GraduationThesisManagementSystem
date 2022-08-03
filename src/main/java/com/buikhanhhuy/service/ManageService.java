package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Manage;

import java.util.List;
import java.util.Map;

public interface ManageService {
    public List<Manage> getManages(Map<String, String> params);
    public long countManage(Map<String, String> params);
    public boolean addManage(Manage manage);
    public Manage getManageById(int manageId);
    public boolean updateManage(int manageId, Manage manage);
    public boolean deleteManage(int manageId);
}
