package com.buikhanhhuy.api.admin;

import com.buikhanhhuy.pojo.Notification;
import com.buikhanhhuy.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController("AdminApiNotificationController")
@Validated
@RequestMapping("/admin/api")
public class ApiNotificationController {
    @Autowired
    private NotificationService notificationService;


    @PostMapping(path = "/notifications", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> addNotification(@Valid @RequestBody Notification notification,
                                                               @RequestParam Map<String, String> params,
                                                               BindingResult result) {
        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            errorMessages = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.notificationService.addNotification(notification, params))
                status = HttpStatus.CREATED;
            else status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(errorMessages, status);
    }

    @DeleteMapping(path = "/notifications/{notificationId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteNotification(@PathVariable("notificationId") int notificationId) {
        this.notificationService.deleteNotification(notificationId);
    }
}
