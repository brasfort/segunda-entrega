package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Disco;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Genero.class)
public class Genero_ { 

    public static volatile SingularAttribute<Genero, Short> id;
    public static volatile SingularAttribute<Genero, String> nombre;
    public static volatile ListAttribute<Genero, Disco> discoList;

}