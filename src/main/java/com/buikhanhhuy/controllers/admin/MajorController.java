package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminMajorController")
@RequestMapping(path = "/admin")
public class MajorController {

    @GetMapping(path = "/majors")
    public String majorList(Model model){
        return "adminMajorList";
    }
}
