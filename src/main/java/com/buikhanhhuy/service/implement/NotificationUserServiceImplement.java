package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.NotificationUser;
import com.buikhanhhuy.repository.NotificationUserRepository;
import com.buikhanhhuy.service.NotificationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationUserServiceImplement implements NotificationUserService {
    @Autowired
    private NotificationUserRepository notificationUserRepository;

    @Override
    public List<NotificationUser> getNotificationUser(int userId) {
        return this.notificationUserRepository.getNotificationUser(userId);
    }

    @Override
    public void turnOffNotification(int notificationUserId) {
        this.notificationUserRepository.turnOffNotification(notificationUserId);
    }
}
