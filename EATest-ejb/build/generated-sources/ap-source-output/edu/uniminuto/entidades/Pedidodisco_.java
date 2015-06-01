package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Discopropietario;
import edu.uniminuto.entidades.Pedido;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Pedidodisco.class)
public class Pedidodisco_ { 

    public static volatile SingularAttribute<Pedidodisco, Integer> id;
    public static volatile SingularAttribute<Pedidodisco, Boolean> enviado;
    public static volatile SingularAttribute<Pedidodisco, Pedido> pedido;
    public static volatile SingularAttribute<Pedidodisco, Discopropietario> discop;
    public static volatile SingularAttribute<Pedidodisco, Date> fechaenvio;
    public static volatile SingularAttribute<Pedidodisco, Boolean> rechazado;
    public static volatile SingularAttribute<Pedidodisco, String> comentario;

}