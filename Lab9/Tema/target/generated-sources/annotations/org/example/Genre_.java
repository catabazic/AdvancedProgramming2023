package org.example;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.example.Album;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-05-06T12:44:01", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Genre.class)
public class Genre_ { 

    public static volatile ListAttribute<Genre, Album> albums;
    public static volatile SingularAttribute<Genre, Integer> ID;
    public static volatile SingularAttribute<Genre, String> NAME;

}