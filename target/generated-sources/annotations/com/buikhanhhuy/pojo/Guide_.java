package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.Thesis;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-07-26T19:32:21")
@StaticMetamodel(Guide.class)
public class Guide_ { 

    public static volatile SingularAttribute<Guide, Thesis> thesis;
    public static volatile SingularAttribute<Guide, Lecturer> lecturer;
    public static volatile SingularAttribute<Guide, Integer> id;

}