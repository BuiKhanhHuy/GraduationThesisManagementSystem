package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.Position;

import java.util.List;
import java.util.Map;

public interface PositionRepository {
    public boolean checkUniquePositionName (String positionName);
    public List<Position> getPositions(Map<String, String> params);
    public List<Object[]> getPositionOptions();
    public long countPosition(Map<String, String> params);
    public boolean addPosition(Position position);
    public Position getPositionById(int positionId);
    public boolean updatePosition(int positionId, Position position);
    public boolean deletePosition(int positionId);
}
