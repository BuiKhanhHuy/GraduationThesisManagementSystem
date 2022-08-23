package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.CouncilDetail;
import com.buikhanhhuy.pojo.SchoolYear;
import com.buikhanhhuy.pojo.Thesis;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(Council.class)
public class Council_ { 

    public static volatile ListAttribute<Council, CouncilDetail> councilDetails;
    public static volatile SingularAttribute<Council, String> name;
    public static volatile SingularAttribute<Council, String> description;
    public static volatile SingularAttribute<Council, SchoolYear> schoolYear;
    public static volatile SingularAttribute<Council, Boolean> block;
    public static volatile SingularAttribute<Council, Integer> id;
    public static volatile SetAttribute<Council, Thesis> theses;

}