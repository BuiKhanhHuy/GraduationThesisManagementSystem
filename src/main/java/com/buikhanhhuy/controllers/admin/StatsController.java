package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.SchoolYearService;
import com.buikhanhhuy.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value = "AdminStatsController")
@RequestMapping(path = "/admin")
public class StatsController {
    @Autowired
    private SchoolYearService schoolYearService;
    @Autowired
    private StatsService statsService;

    @GetMapping(path = "/stats/score-statistics")
    public String scoreStatistics(Model model,
                                  @RequestParam(value = "schoolYearId", required = false) String schoolYearId) {
        Integer schoolYearIdInt = null;

        if (schoolYearId != null && !schoolYearId.isEmpty()) {
            schoolYearIdInt = Integer.parseInt(schoolYearId);
        }

        model.addAttribute("schoolYearOptions", this.schoolYearService.getSchoolYearOptions());
        model.addAttribute("scoreStatistics", this.statsService.thesisScoreStatistics(schoolYearIdInt));

        return "adminScoreStatistics";
    }

    @GetMapping(path = "/stats/frequency-statistics")
    public String frequencyStatistics(Model model,
                                      @RequestParam(value = "schoolYearId", required = false) String schoolYearId) {
        Integer schoolYearIdInt = null;

        if (schoolYearId != null && !schoolYearId.isEmpty()) {
            schoolYearIdInt = Integer.parseInt(schoolYearId);
        }

        model.addAttribute("schoolYearOptions", this.schoolYearService.getSchoolYearOptions());
        model.addAttribute("frequencyStatistics", this.statsService.thesisStatisticsByMajor(schoolYearIdInt));

        return "adminFrequencyStatistics";
    }
}
