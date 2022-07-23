package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.Thesis;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-07-23T23:55:52")
@StaticMetamodel(CounterArgument.class)
public class CounterArgument_ { 

    public static volatile SingularAttribute<CounterArgument, Boolean> allow;
    public static volatile SingularAttribute<CounterArgument, Lecturer> lecturerId;
    public static volatile SingularAttribute<CounterArgument, Double> scores;
    public static volatile SingularAttribute<CounterArgument, Thesis> thesisId;
    public static volatile SingularAttribute<CounterArgument, String> comment;
    public static volatile SingularAttribute<CounterArgument, Integer> id;

}