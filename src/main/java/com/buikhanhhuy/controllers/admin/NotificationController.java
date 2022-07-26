package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.service.NotificationService;
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

    @GetMapping(path = "/notifications")
    public String notificationList (Model model){
        model.addAttribute("notifications", this.notificationService.getNotifications());

        return "adminNotificationList";
    }
}
