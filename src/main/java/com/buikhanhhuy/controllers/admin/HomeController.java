package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.constants.SystemConstant;
import org.springframework.context.annotation.PropertySource;
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
    @ModelAttribute
    public void commonAttribute(Model model) {
        model.addAttribute("pageSize", SystemConstant.PAGE_SIZE);
    }

    @GetMapping(path = "/")
    public String index() {
        return "adminIndex";
    }
}
