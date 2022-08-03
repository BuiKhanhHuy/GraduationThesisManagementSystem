package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Notification;
import com.buikhanhhuy.repository.NotificationRepository;
import com.buikhanhhuy.repository.UserRepository;
import com.buikhanhhuy.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class NotificationServiceImplement implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Notification> getNotifications(Map<String, String> params) {
        return this.notificationRepository.getNotifications(params);
    }

    @Override
    public long countNotification(Map<String, String> params) {
        return this.notificationRepository.countNotification(params);
    }

    @Override
    public boolean addNotification(Notification notification,
                                   Map<String, String> params) {
        Set<Integer> usersId = userRepository.getUsers(params, Arrays.asList(notification.getUsersId()));
        this.notificationRepository.addNotification(notification, usersId);
        return false;
    }
}
