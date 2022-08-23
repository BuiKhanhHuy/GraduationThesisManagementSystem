package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.Lecturer;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(Position.class)
public class Position_ { 

    public static volatile SingularAttribute<Position, String> name;
    public static volatile SingularAttribute<Position, String> description;
    public static volatile SetAttribute<Position, Lecturer> lecturers;
    public static volatile SingularAttribute<Position, Integer> id;

}