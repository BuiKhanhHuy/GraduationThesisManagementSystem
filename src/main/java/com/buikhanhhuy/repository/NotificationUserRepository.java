package com.buikhanhhuy.repository;

import java.util.Set;

public interface NotificationUserRepository {
    public boolean addNotificationUser(int notificationId, Set<Integer> usersId);
}
