package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.Council;
import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.pojo.Thesis;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-07-23T23:55:52")
@StaticMetamodel(SchoolYear.class)
public class SchoolYear_ { 

    public static volatile SetAttribute<SchoolYear, Student> studentSet;
    public static volatile SetAttribute<SchoolYear, Council> councilSet;
    public static volatile SingularAttribute<SchoolYear, Date> endDate;
    public static volatile SingularAttribute<SchoolYear, String> name;
    public static volatile SetAttribute<SchoolYear, Thesis> thesisSet;
    public static volatile SingularAttribute<SchoolYear, Integer> id;
    public static volatile SingularAttribute<SchoolYear, Date> startDate;

}