package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminScoreComponentController")
@RequestMapping(path = "/admin")
public class ScoreComponentController {

    @GetMapping("/score-components")
    public String scoreComponentList (Model model){

        return "adminScoreComponentList";
    }
}
