package com.buikhanhhuy.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "UserHomeController")
public class HomeController {

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }
}
