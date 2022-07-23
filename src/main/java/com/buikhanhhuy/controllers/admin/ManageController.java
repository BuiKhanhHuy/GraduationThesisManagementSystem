package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminManageController")
@RequestMapping(path = "/admin")
public class ManageController {

    @GetMapping(path = "/manages")
    public String manageList (Model model){

        return "adminManageList";
    }
}
