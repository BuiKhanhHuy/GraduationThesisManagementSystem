package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminTopicController")
@RequestMapping(path = "/admin")
public class TopicController {
    @GetMapping(path = "topics")
    public String topicList (Model model){

        return "adminTopicList";
    }
}
