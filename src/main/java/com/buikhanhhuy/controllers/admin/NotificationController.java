package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.NotificationService;
import com.buikhanhhuy.service.RoleService;
import com.buikhanhhuy.service.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String notificationList (Model model){
        model.addAttribute("roles", this.roleService.getRoles(null));
        model.addAttribute("notifications", this.notificationService.getNotifications());
        model.addAttribute("schoolYears", this.schoolYearService.getSchoolYears());
        return "adminNotificationList";
    }
}
