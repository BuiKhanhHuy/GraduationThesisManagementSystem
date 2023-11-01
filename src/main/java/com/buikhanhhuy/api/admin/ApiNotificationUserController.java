package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.service.NotificationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("AdminApiNotificationUserController")
@Validated
@RequestMapping("/admin/api")
public class ApiNotificationUserController {
    @Autowired
    private NotificationUserService notificationUserService;

    @PatchMapping(path = "/notifications-user/{notificationUserId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    public void turnOffNotification(@PathVariable("notificationUserId") int notificationUserId) {
        this.notificationUserService.turnOffNotification(notificationUserId);
    }
}
