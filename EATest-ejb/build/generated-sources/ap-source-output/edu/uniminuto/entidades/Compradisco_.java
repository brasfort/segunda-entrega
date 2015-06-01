package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Compra;
import edu.uniminuto.entidades.Disco;
import edu.uniminuto.entidades.Persona;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Compradisco.class)
public class Compradisco_ { 

    public static volatile SingularAttribute<Compradisco, Integer> id;
    public static volatile SingularAttribute<Compradisco, Compra> compra;
    public static volatile SingularAttribute<Compradisco, Persona> vendedor;
    public static volatile SingularAttribute<Compradisco, Long> precio;
    public static volatile SingularAttribute<Compradisco, Integer> calificacion;
    public static volatile SingularAttribute<Compradisco, Disco> disco;

}