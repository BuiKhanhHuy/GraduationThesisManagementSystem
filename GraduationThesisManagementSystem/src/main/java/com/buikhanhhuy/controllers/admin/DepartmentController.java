package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller(value = "AdminDepartmentController")
@RequestMapping(path = "/admin")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping(path = "/departments")
    public String departmentList(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty())
                ? params.get("page") : "1"));
        model.addAttribute("totalResult", this.departmentService.countDepartment(params));
        model.addAttribute("departments", this.departmentService.getDepartments(params));

        return "adminDepartmentList";
    }
}
