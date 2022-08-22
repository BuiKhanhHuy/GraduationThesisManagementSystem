package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Manage;
import com.buikhanhhuy.repository.ManageRepository;
import com.buikhanhhuy.service.CloudinaryService;
import com.buikhanhhuy.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class ManageServiceImplement implements ManageService {
    @Autowired
    private ManageRepository manageRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public boolean checkUniqueManageEmail(String manageEmail) {
        return this.manageRepository.checkUniqueManageEmail(manageEmail);
    }

    @Override
    public boolean checkUniqueManagePhone(String managePhone) {
        return this.manageRepository.checkUniqueManagePhone(managePhone);
    }

    @Override
    public List<Manage> getManages(Map<String, String> params) {
        return this.manageRepository.getManages(params);
    }

    @Override
    public long countManage(Map<String, String> params) {
        return this.manageRepository.countManage(params);
    }

    @Override
    public boolean addManage(Manage manage, MultipartFile file) {
        if(file != null){
            String avatarStr = this.cloudinaryService.uploadAvatar(file);
            manage.getUser().setAvatar(avatarStr);
        }
        return manageRepository.addManage(manage);
    }

    @Override
    public Manage getManageById(int manageId) {
        return this.manageRepository.getManageById(manageId);
    }

    @Override
    public boolean updateManage(int manageId, Manage manage, MultipartFile file) {
        if(file != null){
            String avatarStr = this.cloudinaryService.uploadAvatar(file);
            manage.getUser().setAvatar(avatarStr);
        }
        return this.manageRepository.updateManage(manageId, manage);
    }

    @Override
    public boolean deleteManage(int manageId) {
        return this.manageRepository.deleteManage(manageId);
    }

}
