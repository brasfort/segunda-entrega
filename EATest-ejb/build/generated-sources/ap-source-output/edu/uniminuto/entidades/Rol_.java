package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Persona;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Rol.class)
public class Rol_ { 

    public static volatile SingularAttribute<Rol, Short> id;
    public static volatile SingularAttribute<Rol, String> nombre;
    public static volatile ListAttribute<Rol, Persona> personaList;

}