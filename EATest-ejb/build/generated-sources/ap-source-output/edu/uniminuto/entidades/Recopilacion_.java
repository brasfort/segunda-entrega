package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Persona;
import edu.uniminuto.entidades.Recocancion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Recopilacion.class)
public class Recopilacion_ { 

    public static volatile SingularAttribute<Recopilacion, Integer> id;
    public static volatile SingularAttribute<Recopilacion, String> nombre;
    public static volatile SingularAttribute<Recopilacion, Boolean> publica;
    public static volatile SingularAttribute<Recopilacion, Date> fecha;
    public static volatile SingularAttribute<Recopilacion, Persona> propietario;
    public static volatile ListAttribute<Recopilacion, Recocancion> recocancionList;

}