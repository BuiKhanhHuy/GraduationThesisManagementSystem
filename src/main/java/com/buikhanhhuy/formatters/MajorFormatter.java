package com.buikhanhhuy.formatters;

import com.buikhanhhuy.pojo.Major;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class MajorFormatter implements Formatter<Major> {
    @Override
    public Major parse(String majorId, Locale locale) throws ParseException {
        Major major = new Major();
        major.setId(Integer.parseInt(majorId));

        return major;
    }

    @Override
    public String print(Major major, Locale locale) {
        return String.valueOf(major.getId());
    }
}
