package com.buikhanhhuy.formatters;

import com.buikhanhhuy.pojo.CouncilDetail;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

public class CouncilDetailFormatter implements Formatter<CouncilDetail> {
    @Override
    public CouncilDetail parse(String councilDetailId, Locale locale) throws ParseException {
        CouncilDetail councilDetail = new CouncilDetail();
        councilDetail.setId(Integer.parseInt(councilDetailId));

        return councilDetail;
    }

    @Override
    public String print(CouncilDetail councilDetail, Locale locale) {
        return Objects.toString(councilDetail.getId());
    }
}
