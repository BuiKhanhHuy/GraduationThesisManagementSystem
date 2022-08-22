package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Manage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ManageService {
    public boolean checkUniqueManageEmail(String manageEmail);
    public boolean checkUniqueManagePhone(String managePhone);
    public List<Manage> getManages(Map<String, String> params);
    public long countManage(Map<String, String> params);
    public boolean addManage(Manage manage, MultipartFile file);
    public Manage getManageById(int manageId);
    public boolean updateManage(int manageId, Manage manage, MultipartFile file);
    public boolean deleteManage(int manageId);
}
