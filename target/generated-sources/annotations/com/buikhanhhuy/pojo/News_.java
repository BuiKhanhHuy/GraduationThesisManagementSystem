package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.User;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-11-02T10:30:09")
@StaticMetamodel(News.class)
public class News_ { 

    public static volatile SingularAttribute<News, Timestamp> createdDate;
    public static volatile SingularAttribute<News, Integer> id;
    public static volatile SingularAttribute<News, String> title;
    public static volatile SingularAttribute<News, User> user;
    public static volatile SingularAttribute<News, String> content;

}