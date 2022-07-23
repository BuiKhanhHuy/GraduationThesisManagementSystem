package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminSchoolYearController")
@RequestMapping(path = "/admin")
public class SchoolYearController {

    @GetMapping(path = "/school-years")
    public String schoolYearList (){
        return "adminSchoolYearList";
    }
}
