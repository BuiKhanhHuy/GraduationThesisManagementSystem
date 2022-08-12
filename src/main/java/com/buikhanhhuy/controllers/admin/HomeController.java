package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.service.EmailService;
import com.buikhanhhuy.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller(value = "AdminHomeController")
@ControllerAdvice
@RequestMapping(path = "/admin")
@PropertySource("classpath:application.properties")
public class HomeController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private ThesisService thesisService;


    @ModelAttribute
    public void commonAttribute(Model model, HttpSession session) {
        model.addAttribute("pageSize", SystemConstant.PAGE_SIZE);
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
    }

    @GetMapping(path = "/")
    public String index() {
        return "adminIndex";
    }

    @GetMapping(path = "/send-mail")
    public String sendMail() {
        Thesis thesis = this.thesisService.getThesisById(23);
        Map<String, Object> model = new HashMap<>();
        model.put("thesis", thesis);

        emailService.sendMail("Thông báo giảng viên phản biện khóa luận tốt nghiệp", new String[]{"khuy220@gmail.com", "1951050027huy@ou.edu.vn"}, model, SystemConstant.REVIEW_LECTURER_EMAIL_TEMPLATE);

        return "adminIndex";
    }
}
