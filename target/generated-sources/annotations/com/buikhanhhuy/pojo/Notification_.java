package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.NotificationUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-05T01:26:34")
@StaticMetamodel(Notification.class)
public class Notification_ { 

    public static volatile SingularAttribute<Notification, Integer> id;
    public static volatile SingularAttribute<Notification, String> title;
    public static volatile SetAttribute<Notification, NotificationUser> notificationUsers;
    public static volatile SingularAttribute<Notification, String> content;

}