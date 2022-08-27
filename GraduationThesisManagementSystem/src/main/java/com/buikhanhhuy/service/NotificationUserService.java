package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.NotificationUser;

import java.util.List;

public interface NotificationUserService {
    public List<NotificationUser> getNotificationUser(int userId);
    public void turnOffNotification(int notificationUserId);
}
