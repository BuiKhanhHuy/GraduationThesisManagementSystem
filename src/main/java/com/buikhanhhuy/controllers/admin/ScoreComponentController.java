package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.ScoreComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminScoreComponentController")
@RequestMapping(path = "/admin")
public class ScoreComponentController {
    @Autowired
    private ScoreComponentService scoreComponentService;

    @GetMapping("/score-components")
    public String scoreComponentList(Model model) {
        model.addAttribute("scoreComponents", this.scoreComponentService.getScoreComponents());

        return "adminScoreComponentList";
    }
}
