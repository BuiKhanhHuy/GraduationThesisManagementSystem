package com.buikhanhhuy.service;

import java.util.List;

public interface StatsService {
    public List<Object[]> thesisStatisticsByMajor(Integer schoolYearId);
    public List<Object[]> thesisScoreStatistics(Integer schoolYearId);
}
