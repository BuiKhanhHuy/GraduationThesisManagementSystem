package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminThesisController")
@RequestMapping(path = "/admin")
public class ThesisController {
    @Autowired
    private ThesisService thesisService;

    @GetMapping(path = "/theses")
    public String getThesisList(Model model) {
        model.addAttribute("theses", this.thesisService.getTheses());

        return "adminThesisList";
    }
}
