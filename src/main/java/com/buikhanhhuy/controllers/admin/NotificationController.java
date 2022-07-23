package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminNotificationController")
@RequestMapping(path = "/admin")
public class NotificationController {

    @GetMapping(path = "/notifications")
    public String notificationList (){

        return "adminNotificationList";
    }
}
