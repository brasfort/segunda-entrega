package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Disco;
import edu.uniminuto.entidades.Pedidodisco;
import edu.uniminuto.entidades.Persona;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Discopropietario.class)
public class Discopropietario_ { 

    public static volatile SingularAttribute<Discopropietario, Integer> id;
    public static volatile SingularAttribute<Discopropietario, Long> precio;
    public static volatile ListAttribute<Discopropietario, Pedidodisco> pedidodiscoList;
    public static volatile SingularAttribute<Discopropietario, Boolean> vendido;
    public static volatile SingularAttribute<Discopropietario, Disco> disco;
    public static volatile SingularAttribute<Discopropietario, Persona> propietario;

}