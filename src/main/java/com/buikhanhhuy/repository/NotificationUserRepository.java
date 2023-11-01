package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.NotificationUser;

import java.util.List;
import java.util.Set;

public interface NotificationUserRepository {
    public boolean addNotificationUser(int notificationId, Set<Integer> usersId);
    public List<NotificationUser> getNotificationUser(int userId);
    public void turnOffNotification(int notificationUserId);
}
