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

    @Override
    public boolean addPosition(Position position) {
        return this.positionRepository.addPosition(position);
    }

    @Override
    public Position getPositionById(int positionId) {
        return this.positionRepository.getPositionById(positionId);
    }

    @Override
    public boolean updatePosition(int positionId, Position position) {
        return this.positionRepository.updatePosition(positionId, position);
    }

    @Override
    public boolean deletePosition(int positionId) {
        return this.positionRepository.deletePosition(positionId);
    }


}
