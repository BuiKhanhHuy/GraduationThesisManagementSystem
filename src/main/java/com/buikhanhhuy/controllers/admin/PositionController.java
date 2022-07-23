package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminPositionController")
@RequestMapping(path = "/admin")
public class PositionController {

    @GetMapping(path = "/positions")
    public String positionList (Model model){
        return "adminPositionList";
    }
}
