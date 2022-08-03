package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.service.DepartmentService;
import com.buikhanhhuy.validators.WebAppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

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
    public String departmentList(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty())
                ? params.get("page") : "1"));
        model.addAttribute("totalResult", this.departmentService.countDepartment(params));
        model.addAttribute("departments", this.departmentService.getDepartments(params));

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
                model.addAttribute("message", "Thêm khoa thành công.");
                return "redirect:/admin/departments";
            }
        }

        model.addAttribute("message", "Thêm khoa thất bại!");
        return "adminAddDepartment";
    }

}
