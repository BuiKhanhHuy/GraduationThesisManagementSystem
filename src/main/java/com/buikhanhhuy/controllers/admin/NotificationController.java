package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.NotificationService;
import com.buikhanhhuy.service.RoleService;
import com.buikhanhhuy.service.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller(value = "AdminNotificationController")
@RequestMapping(path = "/admin")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SchoolYearService schoolYearService;

    @GetMapping(path = "/notifications")
    public String notificationList (Model model, @RequestParam(required = false) Map<String, String> params){
        model.addAttribute("schoolYearOptions", this.schoolYearService.getSchoolYearOptions());
        model.addAttribute("roles", this.roleService.getRoles(null));

        model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty())
                ? params.get("page") : "1"));
        model.addAttribute("totalResult", this.notificationService.countNotification(params));
        model.addAttribute("notifications", this.notificationService.getNotifications(params));
        return "adminNotificationList";
    }
}
