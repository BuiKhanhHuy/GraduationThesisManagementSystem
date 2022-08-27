package com.buikhanhhuy.controllers.user;

import com.buikhanhhuy.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller("UserNewsController")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping(path = "/news")
    public String newsList(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty())
                ? params.get("page") : "1"));
        model.addAttribute("totalResult", this.newsService.countNews(params));
        model.addAttribute("news", this.newsService.getNews(params));

        return "news";
    }

    @GetMapping(path = "/news/{newsId}")
    public String newsList(Model model, @PathVariable(value = "newsId") Integer newsId) {
        model.addAttribute("newsDetail", this.newsService.getNewsWithAuthorById(newsId));
        return "newsDetail";
    }
}
