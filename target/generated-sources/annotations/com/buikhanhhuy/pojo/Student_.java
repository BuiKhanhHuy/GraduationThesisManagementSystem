package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.Major;
import com.buikhanhhuy.pojo.SchoolYear;
import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile SingularAttribute<Student, Date> birthday;
    public static volatile SingularAttribute<Student, String> code;
    public static volatile SingularAttribute<Student, String> address;
    public static volatile SingularAttribute<Student, Integer> gender;
    public static volatile SingularAttribute<Student, String> fullName;
    public static volatile SetAttribute<Student, Thesis> theses;
    public static volatile SingularAttribute<Student, Major> major;
    public static volatile SingularAttribute<Student, String> phone;
    public static volatile SingularAttribute<Student, Double> gpa;
    public static volatile SingularAttribute<Student, SchoolYear> schoolYear;
    public static volatile SingularAttribute<Student, Integer> id;
    public static volatile SingularAttribute<Student, User> user;
    public static volatile SingularAttribute<Student, String> email;

}