package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Council;
import com.buikhanhhuy.repository.CouncilRepository;
import com.buikhanhhuy.service.CouncilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CouncilServiceImplement implements CouncilService {
    @Autowired
    private CouncilRepository councilRepository;

    @Override
    public List<Council> getCouncils(Map<String, String> params) {
        return this.councilRepository.getCouncils(params);
    }

    @Override
    public Council getCouncilById(int councilId) {
        return this.councilRepository.getCouncilById(councilId);
    }

    @Override
    public long countCouncil(Map<String, String> params) {
        return this.councilRepository.countCouncil(params);
    }

    @Override
    public boolean addCouncil(Council council) {
        return this.councilRepository.addCouncil(council);
    }

    @Override
    public boolean deleteCouncil(int councilId) {
        return this.councilRepository.deleteCouncil(councilId);
    }
}
