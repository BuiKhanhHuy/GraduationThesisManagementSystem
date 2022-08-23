package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.ScoreComponent;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(EvaluationMethod.class)
public class EvaluationMethod_ { 

    public static volatile SingularAttribute<EvaluationMethod, String> name;
    public static volatile SingularAttribute<EvaluationMethod, Boolean> active;
    public static volatile ListAttribute<EvaluationMethod, ScoreComponent> scoreComponents;
    public static volatile SingularAttribute<EvaluationMethod, Integer> id;

}