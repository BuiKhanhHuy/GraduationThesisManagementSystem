package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminLecturerController")
@RequestMapping(path = "/admin")
public class LecturerController {

    @GetMapping(path = "/lecturers")
    public String lecturerList(Model model){
        return "adminLecturerList";
    }

}
