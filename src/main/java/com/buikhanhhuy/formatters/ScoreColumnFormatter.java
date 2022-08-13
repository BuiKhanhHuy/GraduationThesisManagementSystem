package com.buikhanhhuy.formatters;

import com.buikhanhhuy.pojo.ScoreColumn;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

public class ScoreColumnFormatter implements Formatter<ScoreColumn> {
    @Override
    public ScoreColumn parse(String scoreColumnId, Locale locale) throws ParseException {
        ScoreColumn scoreColumn = new ScoreColumn();
        scoreColumn.setId(Integer.parseInt(scoreColumnId));

        return scoreColumn;
    }

    @Override
    public String print(ScoreColumn scoreColumn, Locale locale) {
        return Objects.toString(scoreColumn.getId());
    }
}
