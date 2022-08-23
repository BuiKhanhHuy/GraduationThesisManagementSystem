package com.buikhanhhuy.pojo;

import com.buikhanhhuy.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-24T00:31:36")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile SingularAttribute<Role, String> roleName;
    public static volatile SingularAttribute<Role, String> description;
    public static volatile SingularAttribute<Role, Integer> id;
    public static volatile SetAttribute<Role, User> users;

}