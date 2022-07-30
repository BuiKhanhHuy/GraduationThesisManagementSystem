package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.DepartmentService;
import com.buikhanhhuy.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminTopicController")
@RequestMapping(path = "/admin")
public class TopicController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private TopicService topicService;

    @GetMapping(path = "topics")
    public String topicList (Model model){
        model.addAttribute("departmentOptions", this.departmentService.getDepartmentOptions());
        model.addAttribute("topics", this.topicService.getTopics());

        return "adminTopicList";
    }
}
