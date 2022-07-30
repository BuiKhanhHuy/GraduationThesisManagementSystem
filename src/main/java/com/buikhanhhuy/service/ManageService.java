package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Manage;

import java.util.List;

public interface ManageService {
    public List<Manage> getManages();
    public boolean addManage(Manage manage);
    public Manage getManageById(int manageId);
    public boolean updateManage(int manageId, Manage manage);
    public boolean deleteManage(int manageId);
}
