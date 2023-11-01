package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Position;
import com.buikhanhhuy.repository.PositionRepository;
import com.buikhanhhuy.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PositionServiceImplement implements PositionService {
    @Autowired
    private PositionRepository positionRepository;

    @Override
    public boolean checkUniquePositionName(String positionName) {
        return this.positionRepository.checkUniquePositionName(positionName);
    }

    @Override
    public List<Position> getPositions(Map<String, String> params) {
        return this.positionRepository.getPositions(params);
    }

    @Override
    public List<Object[]> getPositionOptions() {
        return this.positionRepository.getPositionOptions();
    }

    @Override
    public long countPosition(Map<String, String> params) {
        return this.positionRepository.countPosition(params);
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
