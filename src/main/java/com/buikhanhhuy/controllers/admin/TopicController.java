package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.DepartmentService;
import com.buikhanhhuy.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller(value = "AdminTopicController")
@RequestMapping(path = "/admin")
public class TopicController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private TopicService topicService;

    @GetMapping(path = "/topics")
    public String topicList (Model model, @RequestParam(required = false) Map<String, String> params){
        model.addAttribute("departmentOptions", this.departmentService.getDepartmentOptions());

        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty())
                ? params.get("page") : "1"));
        model.addAttribute("totalResult", this.topicService.countTopic(params));
        model.addAttribute("topics", this.topicService.getTopics(params));

        return "adminTopicList";
    }
}
