package com.buikhanhhuy.converters;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToLocalDateConverter implements Converter<String, Date> {
    private final String datePattern;

    public StringToLocalDateConverter(String datePattern) {
        this.datePattern = datePattern;
    }

    @Override
    public Date convert(String s) {
        if (!s.isEmpty()) {
            SimpleDateFormat f = new SimpleDateFormat(this.datePattern);
            try {
                return f.parse(s);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
