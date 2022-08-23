package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.pojo.Thesis;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(Topic.class)
public class Topic_ { 

    public static volatile SingularAttribute<Topic, String> name;
    public static volatile SingularAttribute<Topic, String> description;
    public static volatile SingularAttribute<Topic, Integer> id;
    public static volatile SetAttribute<Topic, Thesis> theses;
    public static volatile SingularAttribute<Topic, Department> department;

}