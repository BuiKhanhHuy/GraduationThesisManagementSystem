package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminSchoolYearController")
@RequestMapping(path = "/admin")
public class SchoolYearController {
    @Autowired
    private SchoolYearService schoolYearService;

    @GetMapping(path = "/school-years")
    public String schoolYearList (Model model){
        model.addAttribute("schoolYears", this.schoolYearService.getSchoolYears());

        return "adminSchoolYearList";
    }
}
