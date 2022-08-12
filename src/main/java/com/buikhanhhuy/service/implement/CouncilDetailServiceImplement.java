package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.CouncilDetail;
import com.buikhanhhuy.repository.CouncilDetailRepository;
import com.buikhanhhuy.service.CouncilDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CouncilDetailServiceImplement implements CouncilDetailService {
    @Autowired
    private CouncilDetailRepository councilDetailRepository;

    @Override
    public List<CouncilDetail> getCouncilsDetail(Map<String, String> params) {
        return this.councilDetailRepository.getCouncilsDetail(params);
    }

    @Override
    public List<Object[]> getCouncilsDetail(int lecturerId, Map<String, String> params) {
        return this.councilDetailRepository.getCouncilsDetail(lecturerId, params);
    }

    @Override
    public CouncilDetail getCouncilDetailById(int councilDetailId) {
        return this.councilDetailRepository.getCouncilDetailById(councilDetailId);
    }
}
