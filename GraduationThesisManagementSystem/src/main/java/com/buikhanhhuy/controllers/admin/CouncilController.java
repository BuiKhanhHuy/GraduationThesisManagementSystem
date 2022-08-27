package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.CouncilService;
import com.buikhanhhuy.service.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller(value = "AdminCouncilController")
@RequestMapping(path = "/admin")
public class CouncilController {
    @Autowired
    private CouncilService councilService;
    @Autowired
    private SchoolYearService schoolYearService;

    @GetMapping(path = "/councils")
    public String councilList(Model model, @RequestParam(required = false) Map<String, String> params){
        model.addAttribute("schoolYearOptions", this.schoolYearService.getSchoolYearOptions());

        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty())
                ? params.get("page") : "1"));
        model.addAttribute("totalPage", this.councilService.countCouncil(params));
        model.addAttribute("councils", this.councilService.getCouncils(params));

        return "adminCouncilList";
    }
}
