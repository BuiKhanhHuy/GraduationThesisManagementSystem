package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminMajorController")
@RequestMapping(path = "/admin")
public class MajorController {
    @Autowired
    private MajorService majorService;

    @GetMapping(path = "/majors")
    public String majorList(Model model) {
        model.addAttribute("majors", this.majorService.getMajors());

        return "adminMajorList";
    }
}
