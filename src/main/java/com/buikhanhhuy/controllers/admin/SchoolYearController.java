package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller(value = "AdminSchoolYearController")
@RequestMapping(path = "/admin")
public class SchoolYearController {
    @Autowired
    private SchoolYearService schoolYearService;

    @GetMapping(path = "/school-years")
    public String schoolYearList(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty())
                ? params.get("page") : "1"));
        model.addAttribute("totalResult", this.schoolYearService.countSchoolYear(params));
        model.addAttribute("schoolYears", this.schoolYearService.getSchoolYears(params));

        return "adminSchoolYearList";
    }
}
