package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Manage;
import com.buikhanhhuy.repository.ManageRepository;
import com.buikhanhhuy.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageServiceImplement implements ManageService {
    @Autowired
    private ManageRepository manageRepository;

    @Override
    public List<Manage> getManages() {
        return this.manageRepository.getManages();
    }

    @Override
    public boolean addManage(Manage manage) {
        return manageRepository.addManage(manage);
    }

    @Override
    public Manage getManageById(int manageId) {
        return this.manageRepository.getManageById(manageId);
    }

    @Override
    public boolean updateManage(int manageId, Manage manage) {
        return this.manageRepository.updateManage(manageId, manage);
    }

    @Override
    public boolean deleteManage(int manageId) {
        return this.manageRepository.deleteManage(manageId);
    }

}
