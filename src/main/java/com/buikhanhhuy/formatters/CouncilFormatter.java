package com.buikhanhhuy.formatters;

import com.buikhanhhuy.pojo.Council;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

public class CouncilFormatter implements Formatter<Council> {
    @Override
    public Council parse(String councilId, Locale locale) throws ParseException {
        Council council = new Council();
        council.setId(Integer.parseInt(councilId));

        return council;
    }

    @Override
    public String print(Council council, Locale locale) {
        return Objects.toString(council.getId());
    }
}
