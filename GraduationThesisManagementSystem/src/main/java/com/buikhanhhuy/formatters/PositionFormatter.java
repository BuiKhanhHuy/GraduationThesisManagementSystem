package com.buikhanhhuy.formatters;

import com.buikhanhhuy.pojo.Position;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class PositionFormatter implements Formatter<Position> {
    @Override
    public Position parse(String positionId, Locale locale) throws ParseException {
        Position p = new Position();
        p.setId(Integer.parseInt(positionId));

        return p;
    }

    @Override
    public String print(Position position, Locale locale) {
        return String.valueOf(position.getId());
    }
}
