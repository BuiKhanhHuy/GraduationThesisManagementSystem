package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminDepartmentController")
@RequestMapping(path = "/admin")
public class DepartmentController {

    @GetMapping(path = "/departments")
    public String departmentList(Model model) {

        return "adminDepartmentList";
    }
}
