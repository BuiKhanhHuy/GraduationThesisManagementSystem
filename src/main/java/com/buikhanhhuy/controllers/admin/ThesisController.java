package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller(value = "AdminThesisController")
@RequestMapping(path = "/admin")
public class ThesisController {
    @Autowired
    private ThesisService thesisService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private SchoolYearService schoolYearService;
    @Autowired
    private TopicService topicService;

    @GetMapping(path = "/theses")
    public String getThesisList(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("topicOptions", this.topicService.getTopicOptions());
        model.addAttribute("majorOptions", this.majorService.getMajorOptions());
        model.addAttribute("schoolYearOptions", this.schoolYearService.getSchoolYearOptions());

        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty()) ? params.get("page") : "1"));
        model.addAttribute("totalPage", this.thesisService.countThesis(params));
        model.addAttribute("theses", this.thesisService.getTheses(params));

        return "adminThesisList";
    }
}
