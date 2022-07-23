package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.GuidePK;
import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.Thesis;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-07-23T23:55:52")
@StaticMetamodel(Guide.class)
public class Guide_ { 

    public static volatile SingularAttribute<Guide, Lecturer> lecturerId;
    public static volatile SingularAttribute<Guide, Thesis> thesis;
    public static volatile SingularAttribute<Guide, GuidePK> guidePK;

}