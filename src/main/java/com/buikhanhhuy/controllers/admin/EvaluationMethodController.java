package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminEvaluationMethodController")
@RequestMapping(path = "/admin")
public class EvaluationMethodController {

    @GetMapping("/evaluations-method")
    public String evaluationMethodList (Model model){
        return "adminEvaluationMethodList";
    }
}
