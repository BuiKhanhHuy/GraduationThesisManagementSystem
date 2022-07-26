package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Notification;
import com.buikhanhhuy.repository.NotificationRepository;
import com.buikhanhhuy.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImplement implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> getNotifications() {
        return this.notificationRepository.getNotifications();
    }
}
