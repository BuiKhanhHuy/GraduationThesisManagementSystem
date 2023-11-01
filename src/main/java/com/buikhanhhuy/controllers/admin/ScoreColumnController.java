package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminScoreColumnController")
@RequestMapping(path = "/admin")
public class ScoreColumnController {

    @GetMapping(path = "/score-columns")
    public String scoreColumnList (Model model){

        return "adminScoreColumnList";
    }
}
