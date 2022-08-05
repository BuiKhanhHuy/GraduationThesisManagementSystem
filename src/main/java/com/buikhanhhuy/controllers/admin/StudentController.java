package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.MajorService;
import com.buikhanhhuy.service.SchoolYearService;
import com.buikhanhhuy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller(value = "AdminStudentController")
@RequestMapping(path = "/admin")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private SchoolYearService schoolYearService;

    @GetMapping(path = "/students")
    public String studentList(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("majorOptions", this.majorService.getMajorOptions());
        model.addAttribute("schoolYearOptions", this.schoolYearService.getSchoolYearOptions());

        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty())
                ? params.get("page") : "1"));
        model.addAttribute("totalPage", this.studentService.countStudent(params));
        model.addAttribute("students", this.studentService.getStudents(params));

        return "adminStudentList";
    }
}
