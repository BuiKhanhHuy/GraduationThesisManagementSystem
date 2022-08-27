package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.DepartmentService;
import com.buikhanhhuy.service.LecturerService;
import com.buikhanhhuy.service.PositionService;
import com.buikhanhhuy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller(value = "AdminLecturerController")
@RequestMapping(path = "/admin")
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;

    @GetMapping(path = "/lecturers")
    public String lecturerList(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("positionOptions", this.positionService.getPositionOptions());
        model.addAttribute("departmentOptions", this.departmentService.getDepartmentOptions());
        model.addAttribute("roleOptions", this.roleService.getRoleOptions(new String[]{"ADMIN", "STUDENT"}));

        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty())
                ? params.get("page") : "1"));
        model.addAttribute("totalPage", this.lecturerService.countLecturer(params));
        model.addAttribute("lecturers", this.lecturerService.getLecturers(params));

        return "adminLecturerList";
    }

}
