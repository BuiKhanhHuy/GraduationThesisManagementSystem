package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.ScoreComponent;
import com.buikhanhhuy.pojo.ScoreDetail;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-07-23T23:55:52")
@StaticMetamodel(ScoreColumn.class)
public class ScoreColumn_ { 

    public static volatile SetAttribute<ScoreColumn, ScoreDetail> scoreDetailSet;
    public static volatile SingularAttribute<ScoreColumn, String> name;
    public static volatile SingularAttribute<ScoreColumn, Double> weight;
    public static volatile SingularAttribute<ScoreColumn, ScoreComponent> scoreComponentId;
    public static volatile SingularAttribute<ScoreColumn, Integer> id;

}