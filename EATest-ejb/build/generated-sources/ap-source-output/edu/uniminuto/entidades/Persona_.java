package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Compra;
import edu.uniminuto.entidades.Compradisco;
import edu.uniminuto.entidades.Discopropietario;
import edu.uniminuto.entidades.Pedido;
import edu.uniminuto.entidades.Recopilacion;
import edu.uniminuto.entidades.Rol;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, Long> id;
    public static volatile SingularAttribute<Persona, String> nombres;
    public static volatile ListAttribute<Persona, Pedido> pedidoList;
    public static volatile SingularAttribute<Persona, String> apellidos;
    public static volatile ListAttribute<Persona, Compra> compraList;
    public static volatile ListAttribute<Persona, Discopropietario> discopropietarioList;
    public static volatile ListAttribute<Persona, Recopilacion> recopilacionList;
    public static volatile SingularAttribute<Persona, Date> nacimiento;
    public static volatile ListAttribute<Persona, Compradisco> compradiscoList;
    public static volatile SingularAttribute<Persona, Rol> rol;
    public static volatile SingularAttribute<Persona, String> clave;
    public static volatile SingularAttribute<Persona, String> correo;

}