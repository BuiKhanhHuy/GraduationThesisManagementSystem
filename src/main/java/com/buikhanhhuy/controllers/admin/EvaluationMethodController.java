package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.EvaluationMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller(value = "AdminEvaluationMethodController")
@RequestMapping(path = "/admin")
public class EvaluationMethodController {
    @Autowired
    private EvaluationMethodService evaluationMethodService;

    @GetMapping("/evaluations-method")
    public String evaluationMethodList (Model model, @RequestParam(required = false) Map<String, String> params){
        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty())
                ? params.get("page") : "1"));
        model.addAttribute("totalResult", this.evaluationMethodService.countEvaluationMethod(params));
        model.addAttribute("evaluationMethods", this.evaluationMethodService.getEvaluationMethods());

        return "adminEvaluationMethodList";
    }
}
