package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminNewsController")
@RequestMapping(path = "/admin")
public class NewsController {

    @GetMapping(path="/news")
    public String newsList(){

        return "adminNewsList";
    }

}
