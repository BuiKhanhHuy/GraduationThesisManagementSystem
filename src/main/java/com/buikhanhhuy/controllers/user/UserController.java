package com.buikhanhhuy.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }

}
