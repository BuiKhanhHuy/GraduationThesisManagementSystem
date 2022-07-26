package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminNewsController")
@RequestMapping(path = "/admin")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping(path = "/news")
    public String newsList(Model model) {
        model.addAttribute("news", this.newsService.getNews());

        return "adminNewsList";
    }

}
