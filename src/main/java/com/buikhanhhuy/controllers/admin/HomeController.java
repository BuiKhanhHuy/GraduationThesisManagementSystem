package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminHomeController")
@RequestMapping(path = "/admin")
public class HomeController {

    @GetMapping(path = "/")
    public String index() {
        return "adminIndex";
    }
}
