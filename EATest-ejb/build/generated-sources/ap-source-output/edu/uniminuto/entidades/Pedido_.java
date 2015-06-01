package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Pedidocancion;
import edu.uniminuto.entidades.Pedidodisco;
import edu.uniminuto.entidades.Persona;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, Integer> id;
    public static volatile SingularAttribute<Pedido, BigInteger> total;
    public static volatile ListAttribute<Pedido, Pedidodisco> pedidodiscoList;
    public static volatile SingularAttribute<Pedido, Persona> comprador;
    public static volatile SingularAttribute<Pedido, Date> fechapedido;
    public static volatile ListAttribute<Pedido, Pedidocancion> pedidocancionList;
    public static volatile SingularAttribute<Pedido, Boolean> descartado;

}