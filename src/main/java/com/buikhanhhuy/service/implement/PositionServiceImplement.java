package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Position;
import com.buikhanhhuy.repository.PositionRepository;
import com.buikhanhhuy.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImplement implements PositionService {
    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> getPositions() {
        return this.positionRepository.getPositions();
    }
}
