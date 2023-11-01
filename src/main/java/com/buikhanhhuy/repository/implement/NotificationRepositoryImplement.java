package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Notification;
import com.buikhanhhuy.repository.NotificationRepository;
import com.buikhanhhuy.repository.NotificationUserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
@Transactional
public class NotificationRepositoryImplement implements NotificationRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;
    @Autowired
    private NotificationUserRepository notificationUserRepository;

    @Override
    public List<Notification> getNotifications(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Notification> query = builder.createQuery(Notification.class);
        Root<Notification> root = query.from(Notification.class);
        query.select(root);

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");
            query.where(builder.like(root.get("title").as(String.class), String.format("%%%s%%", kw)));
        }
        query.orderBy(builder.desc(root.get("id")));

        int page = 1;
        int pageSize = SystemConstant.PAGE_SIZE;

        if (params.containsKey("page") && !params.get("page").isEmpty()) page = Integer.parseInt(params.get("page"));

        Query q = session.createQuery(query);

        int startPage = (page - 1) * pageSize;
        q.setFirstResult(startPage);
        q.setMaxResults(pageSize);

        return q.getResultList();
    }

    @Override
    public long countNotification(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Notification> root = query.from(Notification.class);
        query.multiselect(builder.count(root.get("id")));

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");
            query.where(builder.like(root.get("title").as(String.class), String.format("%%%s%%", kw)));
        }

        Query q = session.createQuery(query);
        Object result = q.getSingleResult();

        return (long) result;
    }

    @Override
    public boolean addNotification(Notification notification, Set<Integer> usersId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            session.save(notification);
            this.notificationUserRepository.addNotificationUser(notification.getId(), usersId);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteNotification(int notificationId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Notification objNotification = session.get(Notification.class, notificationId);
            session.delete(objNotification);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
