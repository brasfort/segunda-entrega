package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Compracancion;
import edu.uniminuto.entidades.Discocancion;
import edu.uniminuto.entidades.Interprete;
import edu.uniminuto.entidades.Pedidocancion;
import edu.uniminuto.entidades.Recocancion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Cancion.class)
public class Cancion_ { 

    public static volatile SingularAttribute<Cancion, Long> id;
    public static volatile SingularAttribute<Cancion, String> nombre;
    public static volatile SingularAttribute<Cancion, String> peso;
    public static volatile ListAttribute<Cancion, Discocancion> discocancionList;
    public static volatile SingularAttribute<Cancion, Integer> precio;
    public static volatile ListAttribute<Cancion, Compracancion> compracancionList;
    public static volatile SingularAttribute<Cancion, String> anhio;
    public static volatile ListAttribute<Cancion, Pedidocancion> pedidocancionList;
    public static volatile SingularAttribute<Cancion, Interprete> interprete;
    public static volatile SingularAttribute<Cancion, Date> duracion;
    public static volatile ListAttribute<Cancion, Recocancion> recocancionList;
    public static volatile SingularAttribute<Cancion, String> calidad;

}