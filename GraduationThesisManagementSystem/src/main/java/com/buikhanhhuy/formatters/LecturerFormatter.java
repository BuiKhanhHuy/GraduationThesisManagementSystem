package com.buikhanhhuy.formatters;

import com.buikhanhhuy.pojo.Lecturer;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class LecturerFormatter implements Formatter<Lecturer> {
    @Override
    public Lecturer parse(String lecturerId, Locale locale) throws ParseException {
        Lecturer l = new Lecturer();
        l.setId(Integer.parseInt(lecturerId));
        return l;
    }

    @Override
    public String print(Lecturer lecturer, Locale locale) {
        return String.valueOf(lecturer.getId());
    }
}
