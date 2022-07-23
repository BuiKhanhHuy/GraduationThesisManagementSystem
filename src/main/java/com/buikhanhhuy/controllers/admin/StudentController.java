package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminStudentController")
@RequestMapping(path = "/admin")
public class StudentController {
    @GetMapping(path = "/students")
    public String studentList(Model model){

        return "adminStudentList";
    }
}
