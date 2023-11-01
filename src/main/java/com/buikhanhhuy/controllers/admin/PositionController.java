package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller(value = "AdminPositionController")
@RequestMapping(path = "/admin")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @GetMapping(path = "/positions")
    public String positionList(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty())
                ? params.get("page") : "1"));
        model.addAttribute("totalResult",this.positionService.countPosition(params));
        model.addAttribute("positions", this.positionService.getPositions(params));

        return "adminPositionList";
    }
}
