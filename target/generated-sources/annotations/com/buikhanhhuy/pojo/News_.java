package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-07-23T23:55:52")
@StaticMetamodel(News.class)
public class News_ { 

    public static volatile SingularAttribute<News, Integer> id;
    public static volatile SingularAttribute<News, String> title;
    public static volatile SingularAttribute<News, User> userId;
    public static volatile SingularAttribute<News, String> content;

}