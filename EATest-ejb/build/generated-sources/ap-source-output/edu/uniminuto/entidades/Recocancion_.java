package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Cancion;
import edu.uniminuto.entidades.Recopilacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Recocancion.class)
public class Recocancion_ { 

    public static volatile SingularAttribute<Recocancion, Integer> id;
    public static volatile SingularAttribute<Recocancion, Cancion> cancion;
    public static volatile SingularAttribute<Recocancion, Recopilacion> recopilacion;

}