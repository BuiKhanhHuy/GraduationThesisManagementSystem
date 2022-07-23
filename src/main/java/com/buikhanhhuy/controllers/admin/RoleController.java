package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminRoleController")
@RequestMapping(path = "/admin")
public class RoleController {

    @GetMapping(path = "/roles")
    public String roleList (Model model){

        return "adminRoleList";
    }
}
