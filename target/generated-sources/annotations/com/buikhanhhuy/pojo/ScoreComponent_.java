package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.EvaluationMethod;
import com.buikhanhhuy.pojo.ScoreColumn;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(ScoreComponent.class)
public class ScoreComponent_ { 

    public static volatile ListAttribute<ScoreComponent, ScoreColumn> scoreColumns;
    public static volatile SingularAttribute<ScoreComponent, EvaluationMethod> evaluationMethod;
    public static volatile SingularAttribute<ScoreComponent, String> name;
    public static volatile SingularAttribute<ScoreComponent, Integer> id;

}