package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Position;

import java.util.List;

public interface PositionService {
    public List<Position> getPositions();
    public boolean addPosition(Position position);
    public Position getPositionById(int positionId);
    public boolean updatePosition(int positionId, Position position);
    public boolean deletePosition(int positionId);
}
