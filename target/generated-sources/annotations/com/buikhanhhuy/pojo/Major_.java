package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.pojo.Thesis;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(Major.class)
public class Major_ { 

    public static volatile SingularAttribute<Major, String> code;
    public static volatile SingularAttribute<Major, String> name;
    public static volatile SingularAttribute<Major, String> description;
    public static volatile SetAttribute<Major, Student> students;
    public static volatile SingularAttribute<Major, Integer> id;
    public static volatile SetAttribute<Major, Thesis> theses;
    public static volatile SingularAttribute<Major, Department> department;

}