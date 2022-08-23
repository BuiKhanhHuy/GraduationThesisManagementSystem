package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.Manage;
import com.buikhanhhuy.pojo.News;
import com.buikhanhhuy.pojo.NotificationUser;
import com.buikhanhhuy.pojo.Role;
import com.buikhanhhuy.pojo.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SetAttribute<User, News> news;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Role> role;
    public static volatile SingularAttribute<User, Student> student;
    public static volatile SingularAttribute<User, Boolean> active;
    public static volatile SingularAttribute<User, Lecturer> lecturer;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SetAttribute<User, NotificationUser> notificationUsers;
    public static volatile SingularAttribute<User, Manage> manage;
    public static volatile SingularAttribute<User, String> username;

}