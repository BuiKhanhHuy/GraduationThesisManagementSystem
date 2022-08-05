package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.Council;
import com.buikhanhhuy.pojo.CounterArgument;
import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.pojo.Guide;
import com.buikhanhhuy.pojo.Perform;
import com.buikhanhhuy.pojo.SchoolYear;
import com.buikhanhhuy.pojo.Score;
import com.buikhanhhuy.pojo.Topic;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-06T00:12:13")
@StaticMetamodel(Thesis.class)
public class Thesis_ { 

    public static volatile SetAttribute<Thesis, Perform> performs;
    public static volatile SingularAttribute<Thesis, Date> thesisEndDate;
    public static volatile SingularAttribute<Thesis, String> code;
    public static volatile SetAttribute<Thesis, Score> scores;
    public static volatile SingularAttribute<Thesis, String> reportFile;
    public static volatile SingularAttribute<Thesis, Date> complateDate;
    public static volatile SingularAttribute<Thesis, Double> totalScore;
    public static volatile SingularAttribute<Thesis, Boolean> result;
    public static volatile SetAttribute<Thesis, Guide> guides;
    public static volatile SingularAttribute<Thesis, Date> thesisStartDate;
    public static volatile SingularAttribute<Thesis, Council> council;
    public static volatile SetAttribute<Thesis, CounterArgument> counterArguments;
    public static volatile SingularAttribute<Thesis, SchoolYear> schoolYear;
    public static volatile SingularAttribute<Thesis, Topic> topic;
    public static volatile SingularAttribute<Thesis, String> comment;
    public static volatile SingularAttribute<Thesis, Integer> id;
    public static volatile SingularAttribute<Thesis, Department> department;
    public static volatile SingularAttribute<Thesis, Date> startDate;
    public static volatile SingularAttribute<Thesis, Integer> status;

}