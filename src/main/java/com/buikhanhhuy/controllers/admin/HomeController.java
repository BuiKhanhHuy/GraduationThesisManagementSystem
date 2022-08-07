package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller(value = "AdminHomeController")
@ControllerAdvice
@RequestMapping(path = "/admin")
@PropertySource("classpath:application.properties")
public class HomeController {
    @Autowired
    private EmailService emailService;


    @ModelAttribute
    public void commonAttribute(Model model) {
        model.addAttribute("pageSize", SystemConstant.PAGE_SIZE);
    }

    @GetMapping(path = "/")
    public String index() {
        return "adminIndex";
    }

    @GetMapping(path = "/send-mail")
    public String sendMail() {
        Map<String, String> model = new HashMap<>();
        model.put("fullName", "Huy Bui Khanh");

        emailService.sendMail("Gửi mail demo", new String[]{"khuy220@gmail.com", "1951050027huy@ou.edu.vn"},
                model, SystemConstant.REVIEW_LECTURER_EMAIL_TEMPLATE);

        return "adminIndex";
    }
}
