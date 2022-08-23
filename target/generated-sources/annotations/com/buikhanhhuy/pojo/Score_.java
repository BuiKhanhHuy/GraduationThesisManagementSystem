package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.CouncilDetail;
import com.buikhanhhuy.pojo.ScoreDetail;
import com.buikhanhhuy.pojo.Thesis;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(Score.class)
public class Score_ { 

    public static volatile SingularAttribute<Score, Thesis> thesis;
    public static volatile ListAttribute<Score, ScoreDetail> scoreDetails;
    public static volatile SingularAttribute<Score, CouncilDetail> councilDetail;
    public static volatile SingularAttribute<Score, Integer> id;

}