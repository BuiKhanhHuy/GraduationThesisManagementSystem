package com.buikhanhhuy.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminHomeController")
@ControllerAdvice
@RequestMapping(path = "/admin")
@PropertySource("classpath:application.properties")
public class HomeController {
    @Autowired
    private Environment environment;

    @ModelAttribute
    public void commonAttribute(Model model) {
        model.addAttribute("pageSize", environment.getProperty("pageSize"));
    }

    @GetMapping(path = "/")
    public String index() {
        return "adminIndex";
    }
}
