package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminStudentController")
@RequestMapping(path = "/admin")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping(path = "/students")
    public String studentList(Model model) {
        model.addAttribute("students", this.studentService.getStudents());

        return "adminStudentList";
    }
}
