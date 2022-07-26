package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminPositionController")
@RequestMapping(path = "/admin")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @GetMapping(path = "/positions")
    public String positionList(Model model) {
        model.addAttribute("positions", this.positionService.getPositions());

        return "adminPositionList";
    }
}
