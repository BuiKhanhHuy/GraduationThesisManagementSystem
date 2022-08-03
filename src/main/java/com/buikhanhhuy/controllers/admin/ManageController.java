package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utils.Utils;

import java.util.Map;

@Controller(value = "AdminManageController")
@RequestMapping(path = "/admin")
public class ManageController {
    @Autowired
    private ManageService manageService;

    @GetMapping(path = "/manages")
    public String manageList(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty())
                ? params.get("page") : "1"));
        model.addAttribute("totalPage", this.manageService.countManage(params));
        model.addAttribute("manages", this.manageService.getManages(params));

        return "adminManageList";
    }
}
