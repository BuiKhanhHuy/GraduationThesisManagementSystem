package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Major;
import com.buikhanhhuy.pojo.Notification;
import com.buikhanhhuy.pojo.NotificationUser;
import com.buikhanhhuy.pojo.User;
import com.buikhanhhuy.repository.NotificationUserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class NotificationUserRepositoryImplement implements NotificationUserRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public boolean addNotificationUser(int notificationId, Set<Integer> usersId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            if (usersId != null) {
                for (Integer userId : usersId) {
                    NotificationUser notificationUser = new NotificationUser();
                    notificationUser.setUser(session.get(User.class, userId));
                    notificationUser.setNotification(session.get(Notification.class, notificationId));

                    session.save(notificationUser);
                }
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public List<NotificationUser> getNotificationUser(int userId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<NotificationUser> query = builder.createQuery(NotificationUser.class);
            Root<NotificationUser> root = query.from(NotificationUser.class);
            query.select(root);
            query.where(builder.equal(root.get("user"), userId),
                    builder.equal(root.get("active"), true));

            query.orderBy(builder.desc(root.get("id")));

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void turnOffNotification(int notificationUserId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try{
            NotificationUser notificationUser = session.get(NotificationUser.class, notificationUserId);
            notificationUser.setActive(false);

            session.update(notificationUser);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
