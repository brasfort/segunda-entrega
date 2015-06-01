package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Cancion;
import edu.uniminuto.entidades.Compra;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Compracancion.class)
public class Compracancion_ { 

    public static volatile SingularAttribute<Compracancion, Integer> id;
    public static volatile SingularAttribute<Compracancion, Compra> compra;
    public static volatile SingularAttribute<Compracancion, Integer> precio;
    public static volatile SingularAttribute<Compracancion, Cancion> cancion;

}