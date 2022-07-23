package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.Major;
import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.pojo.Topic;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-07-23T23:55:51")
@StaticMetamodel(Department.class)
public class Department_ { 

    public static volatile SingularAttribute<Department, String> code;
    public static volatile SetAttribute<Department, Topic> topicSet;
    public static volatile SetAttribute<Department, Lecturer> lecturerSet;
    public static volatile SetAttribute<Department, Major> majorSet;
    public static volatile SingularAttribute<Department, String> name;
    public static volatile SingularAttribute<Department, String> description;
    public static volatile SetAttribute<Department, Thesis> thesisSet;
    public static volatile SingularAttribute<Department, Integer> id;
    public static volatile SingularAttribute<Department, Date> founding;

}