package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.CouncilDetail;
import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.pojo.Position;
import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(Lecturer.class)
public class Lecturer_ { 

    public static volatile SingularAttribute<Lecturer, Date> birthday;
    public static volatile SingularAttribute<Lecturer, String> code;
    public static volatile SingularAttribute<Lecturer, String> address;
    public static volatile SingularAttribute<Lecturer, Integer> gender;
    public static volatile SingularAttribute<Lecturer, String> fullName;
    public static volatile SetAttribute<Lecturer, Thesis> theses;
    public static volatile SetAttribute<Lecturer, CouncilDetail> councilDetails;
    public static volatile SingularAttribute<Lecturer, String> phone;
    public static volatile SetAttribute<Lecturer, Thesis> reviewTheses;
    public static volatile SingularAttribute<Lecturer, Integer> id;
    public static volatile SingularAttribute<Lecturer, Position> position;
    public static volatile SingularAttribute<Lecturer, Department> department;
    public static volatile SingularAttribute<Lecturer, User> user;
    public static volatile SingularAttribute<Lecturer, String> email;

}