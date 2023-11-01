package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.ScoreComponent;
import com.buikhanhhuy.repository.ScoreComponentRepository;
import com.buikhanhhuy.service.ScoreComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreComponentServiceImplement implements ScoreComponentService {
    @Autowired
    private ScoreComponentRepository scoreComponentRepository;

    @Override
    public List<ScoreComponent> getScoreComponents() {
        return this.scoreComponentRepository.getScoreComponents();
    }
}
