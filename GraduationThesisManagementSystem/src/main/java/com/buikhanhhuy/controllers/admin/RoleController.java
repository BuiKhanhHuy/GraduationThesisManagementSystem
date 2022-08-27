package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value = "AdminRoleController")
@RequestMapping(path = "/admin")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping(path = "/roles")
    public String roleList(Model model, @RequestParam(value = "kw", required = false) String kw) {
        model.addAttribute("roles", this.roleService.getRoles(kw));

        return "adminRoleList";
    }
}
