package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.EvaluationMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminEvaluationMethodController")
@RequestMapping(path = "/admin")
public class EvaluationMethodController {
    @Autowired
    private EvaluationMethodService evaluationMethodService;

    @GetMapping("/evaluations-method")
    public String evaluationMethodList (Model model){
        model.addAttribute("evaluationMethods", this.evaluationMethodService.getEvaluationMethods());

        return "adminEvaluationMethodList";
    }
}
