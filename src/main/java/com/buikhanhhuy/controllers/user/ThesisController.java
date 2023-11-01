package com.buikhanhhuy.controllers.user;

import com.buikhanhhuy.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller(value = "UserThesisController")
public class ThesisController {
    @Autowired
    private ThesisService thesisService;

}
