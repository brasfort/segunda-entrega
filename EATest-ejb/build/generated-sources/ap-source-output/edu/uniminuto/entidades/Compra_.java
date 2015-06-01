package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Compracancion;
import edu.uniminuto.entidades.Compradisco;
import edu.uniminuto.entidades.Persona;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile SingularAttribute<Compra, Integer> id;
    public static volatile SingularAttribute<Compra, Long> total;
    public static volatile SingularAttribute<Compra, Date> fecha;
    public static volatile ListAttribute<Compra, Compracancion> compracancionList;
    public static volatile SingularAttribute<Compra, Persona> comprador;
    public static volatile ListAttribute<Compra, Compradisco> compradiscoList;
    public static volatile SingularAttribute<Compra, String> observaciones;

}