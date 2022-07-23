package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.CouncilDetail;
import com.buikhanhhuy.pojo.SchoolYear;
import com.buikhanhhuy.pojo.Thesis;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-07-23T23:55:51")
@StaticMetamodel(Council.class)
public class Council_ { 

    public static volatile SingularAttribute<Council, String> name;
    public static volatile SetAttribute<Council, CouncilDetail> councilDetailSet;
    public static volatile SingularAttribute<Council, String> description;
    public static volatile SetAttribute<Council, Thesis> thesisSet;
    public static volatile SingularAttribute<Council, Integer> id;
    public static volatile SingularAttribute<Council, SchoolYear> schoolYearId;

}