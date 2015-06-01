package edu.uniminuto.entidades;

import edu.uniminuto.entidades.Compradisco;
import edu.uniminuto.entidades.Discocancion;
import edu.uniminuto.entidades.Discopropietario;
import edu.uniminuto.entidades.Genero;
import edu.uniminuto.entidades.Interprete;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-27T04:00:16")
@StaticMetamodel(Disco.class)
public class Disco_ { 

    public static volatile SingularAttribute<Disco, Integer> id;
    public static volatile SingularAttribute<Disco, String> nombre;
    public static volatile SingularAttribute<Disco, String> imagen;
    public static volatile SingularAttribute<Disco, Genero> genero;
    public static volatile ListAttribute<Disco, Discocancion> discocancionList;
    public static volatile ListAttribute<Disco, Discopropietario> discopropietarioList;
    public static volatile ListAttribute<Disco, Compradisco> compradiscoList;
    public static volatile SingularAttribute<Disco, Date> anhio;
    public static volatile SingularAttribute<Disco, Interprete> interprete;

}