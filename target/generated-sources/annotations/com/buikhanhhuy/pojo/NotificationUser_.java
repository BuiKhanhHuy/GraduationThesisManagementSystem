package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.Notification;
import com.buikhanhhuy.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(NotificationUser.class)
public class NotificationUser_ { 

    public static volatile SingularAttribute<NotificationUser, Notification> notification;
    public static volatile SingularAttribute<NotificationUser, Boolean> active;
    public static volatile SingularAttribute<NotificationUser, Integer> id;
    public static volatile SingularAttribute<NotificationUser, User> user;

}