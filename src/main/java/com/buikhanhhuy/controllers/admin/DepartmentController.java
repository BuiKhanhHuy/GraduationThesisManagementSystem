package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.service.DepartmentService;
import com.buikhanhhuy.validators.WebAppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller(value = "AdminDepartmentController")
@RequestMapping(path = "/admin")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private WebAppValidator departmentValidator;

    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.setValidator(departmentValidator);
    }

    @GetMapping(path = "/departments")
    public String departmentList(Model model) {
        model.addAttribute("departments", this.departmentService.getDepartments());

        return "adminDepartmentList";
    }

    @GetMapping(path = "/departments/add")
    public String addDepartmentView(Model model) {
        model.addAttribute("department", new Department());

        return "adminAddDepartment";
    }

    @PostMapping(path = "/departments/add")
    public String addDepartment(Model model, @ModelAttribute(value = "department") @Valid Department department, BindingResult result) {
        if (!result.hasErrors()) {
            if (this.departmentService.addDepartment(department)) {
                return "redirect:/admin/departments";
            }
        }

        return "adminAddDepartment";
    }

}
