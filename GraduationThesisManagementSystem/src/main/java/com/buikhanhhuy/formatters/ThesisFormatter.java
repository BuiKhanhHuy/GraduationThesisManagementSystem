package com.buikhanhhuy.formatters;

import com.buikhanhhuy.pojo.Thesis;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

public class ThesisFormatter implements Formatter<Thesis> {
    @Override
    public Thesis parse(String thesisId, Locale locale) throws ParseException {
        Thesis thesis = new Thesis();
        thesis.setId(Integer.parseInt(thesisId));

        return thesis;
    }

    @Override
    public String print(Thesis thesis, Locale locale) {
        return Objects.toString(thesis.getId());
    }
}
