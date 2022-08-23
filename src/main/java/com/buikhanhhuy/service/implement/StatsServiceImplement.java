package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.repository.StatsRepository;
import com.buikhanhhuy.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsServiceImplement implements StatsService {
    @Autowired
    private StatsRepository statsRepository;

    @Override
    public List<Object[]> thesisStatisticsByMajor(Integer schoolYearId) {
        return this.statsRepository.thesisStatisticsByMajor(schoolYearId);
    }

    @Override
    public List<Object[]> thesisScoreStatistics(Integer schoolYearId) {
        return this.statsRepository.thesisScoreStatistics(schoolYearId);
    }
}
