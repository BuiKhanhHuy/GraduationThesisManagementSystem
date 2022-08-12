package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.DepartmentService;
import com.buikhanhhuy.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller(value = "AdminMajorController")
@RequestMapping(path = "/admin")
public class MajorController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private MajorService majorService;

    @GetMapping(path = "/majors")
    public String majorList(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("departmentOptions", this.departmentService.getDepartmentOptions());

        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty())
                ? params.get("page") : "1"));
        model.addAttribute("totalResult", this.majorService.countMajor(params));
        model.addAttribute("majors", this.majorService.getMajors(params));

        return "adminMajorList";
    }
}
