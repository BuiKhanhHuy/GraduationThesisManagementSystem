package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.Council;
import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.Score;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(CouncilDetail.class)
public class CouncilDetail_ { 

    public static volatile SetAttribute<CouncilDetail, Score> scores;
    public static volatile SingularAttribute<CouncilDetail, Council> council;
    public static volatile SingularAttribute<CouncilDetail, Lecturer> lecturer;
    public static volatile SingularAttribute<CouncilDetail, Integer> id;
    public static volatile SingularAttribute<CouncilDetail, String> position;

}