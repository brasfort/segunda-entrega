package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Cancion;
import edu.uniminuto.entidades.Disco;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Discocancion.class)
public class Discocancion_ { 

    public static volatile SingularAttribute<Discocancion, Long> id;
    public static volatile SingularAttribute<Discocancion, Disco> disco;
    public static volatile SingularAttribute<Discocancion, Cancion> cancion;

}