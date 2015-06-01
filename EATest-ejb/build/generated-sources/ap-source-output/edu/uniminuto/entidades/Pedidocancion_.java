package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Cancion;
import edu.uniminuto.entidades.Pedido;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Pedidocancion.class)
public class Pedidocancion_ { 

    public static volatile SingularAttribute<Pedidocancion, Integer> id;
    public static volatile SingularAttribute<Pedidocancion, Pedido> pedido;
    public static volatile SingularAttribute<Pedidocancion, Cancion> cancion;

}