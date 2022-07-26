package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminManageController")
@RequestMapping(path = "/admin")
public class ManageController {
    @Autowired
    private ManageService manageService;

    @GetMapping(path = "/manages")
    public String manageList (Model model){
        model.addAttribute("manages", this.manageService.getManages());

        return "adminManageList";
    }
}
