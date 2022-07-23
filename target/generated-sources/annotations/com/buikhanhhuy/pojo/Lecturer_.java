package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.CouncilDetail;
import com.buikhanhhuy.pojo.CounterArgument;
import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.pojo.Guide;
import com.buikhanhhuy.pojo.Position;
import com.buikhanhhuy.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-07-23T23:55:52")
@StaticMetamodel(Lecturer.class)
public class Lecturer_ { 

    public static volatile SingularAttribute<Lecturer, Date> birthday;
    public static volatile SingularAttribute<Lecturer, String> code;
    public static volatile SingularAttribute<Lecturer, String> address;
    public static volatile SingularAttribute<Lecturer, Integer> gender;
    public static volatile SetAttribute<Lecturer, CounterArgument> counterArgumentSet;
    public static volatile SingularAttribute<Lecturer, Department> departmentId;
    public static volatile SingularAttribute<Lecturer, String> fullName;
    public static volatile SetAttribute<Lecturer, Guide> guideSet;
    public static volatile SingularAttribute<Lecturer, User> userId;
    public static volatile SingularAttribute<Lecturer, Position> positionId;
    public static volatile SingularAttribute<Lecturer, String> phone;
    public static volatile SetAttribute<Lecturer, CouncilDetail> councilDetailSet;
    public static volatile SingularAttribute<Lecturer, Integer> id;
    public static volatile SingularAttribute<Lecturer, String> email;

}