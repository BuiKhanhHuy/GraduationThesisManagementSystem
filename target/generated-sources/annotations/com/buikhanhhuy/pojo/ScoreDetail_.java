package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.Score;
import com.buikhanhhuy.pojo.ScoreColumn;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(ScoreDetail.class)
public class ScoreDetail_ { 

    public static volatile SingularAttribute<ScoreDetail, Score> score;
    public static volatile SingularAttribute<ScoreDetail, ScoreColumn> scoreColumn;
    public static volatile SingularAttribute<ScoreDetail, Integer> id;
    public static volatile SingularAttribute<ScoreDetail, Double> scoreNum;

}