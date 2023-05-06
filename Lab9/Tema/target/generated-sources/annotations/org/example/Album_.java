package org.example;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.example.Artist;
import org.example.Genre;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-05-06T12:44:01", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Album.class)
public class Album_ { 

    public static volatile SingularAttribute<Album, String> ARTIST;
    public static volatile SingularAttribute<Album, Artist> artist;
    public static volatile ListAttribute<Album, Genre> genres;
    public static volatile SingularAttribute<Album, String> GENRES;
    public static volatile SingularAttribute<Album, Integer> RELEASE_YEAR;
    public static volatile SingularAttribute<Album, Integer> ID;
    public static volatile SingularAttribute<Album, String> NAME;

}