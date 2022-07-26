package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminLecturerController")
@RequestMapping(path = "/admin")
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;

    @GetMapping(path = "/lecturers")
    public String lecturerList(Model model){
        model.addAttribute("lecturers", this.lecturerService.getLecturers());

        return "adminLecturerList";
    }

}
