package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.NewsService;
import com.buikhanhhuy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller(value = "AdminNewsController")
@RequestMapping(path = "/admin")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private RoleService roleService;

    @GetMapping(path = "/news")
    public String newsList(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty())
                ? params.get("page") : "1"));
        model.addAttribute("totalResult", this.newsService.countNews(params));
        model.addAttribute("news", this.newsService.getNews(params));

        return "adminNewsList";
    }

}
