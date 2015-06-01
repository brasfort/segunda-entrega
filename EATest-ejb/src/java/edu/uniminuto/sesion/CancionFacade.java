/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.sesion;

import edu.uniminuto.entidades.Cancion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jhon Gómez
 */
@Stateless
public class CancionFacade extends AbstractFacade<Cancion> {
    @PersistenceContext(unitName = "EATest-ejbPU")
    private EntityManager em;

    
    private final int MAX_REGISTROS = 20;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CancionFacade() {
        super(Cancion.class);
    }
    
    
    public List<Cancion> getCanciones() {
        return getCanciones(0);
    }
    
    
    public List<Cancion> getCanciones(long ultimoId) {
        try {

            //"FROM Persona p WHERE p.correo = :correo AND p.clave := clave", 
            String querystr = "SELECT c FROM Cancion c WHERE c.id > :id ORDER BY c.id";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Cancion.class);

            q.setParameter("id", ultimoId);
            q.setMaxResults(MAX_REGISTROS);

            return q.getResultList();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        return null;
    }
    
    public List<Cancion> getCanciones(String busqueda) {
        List<Cancion> canciones = new ArrayList<>();
        if (busqueda.length() > 3) {
            try {

                String querystr = "SELECT c FROM Cancion c WHERE c.nombre LIKE '%" + busqueda + "%'";

                javax.persistence.Query q = getEntityManager().createQuery(querystr, Cancion.class);


                canciones = q.getResultList();
            } catch (Exception ex) {
                String exdf = ex.getMessage();
            }
        }
        return canciones;
    }
    
}
