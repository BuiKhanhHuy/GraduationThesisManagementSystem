package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.EvaluationMethod;
import com.buikhanhhuy.pojo.ScoreColumn;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-07-23T23:55:52")
@StaticMetamodel(ScoreComponent.class)
public class ScoreComponent_ { 

    public static volatile SetAttribute<ScoreComponent, ScoreColumn> scoreColumnSet;
    public static volatile SingularAttribute<ScoreComponent, String> name;
    public static volatile SingularAttribute<ScoreComponent, Integer> id;
    public static volatile SingularAttribute<ScoreComponent, EvaluationMethod> evaluationMothodId;

}