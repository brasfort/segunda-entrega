/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.sesion;

import edu.uniminuto.entidades.Recocancion;
import edu.uniminuto.entidades.Recopilacion;
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
public class RecopilacionFacade extends AbstractFacade<Recopilacion> {
    @PersistenceContext(unitName = "EATest-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecopilacionFacade() {
        super(Recopilacion.class);
    }
    
    

    public List<Recopilacion> getRecopilacionesAgregadas(long personaId, long cancionId) {

        List<Recopilacion> recos = new ArrayList<>();
        try {
            String querystr = "SELECT DISTINCT r FROM Recopilacion r, "
                    + "Recocancion rc WHERE rc.recopilacion.id = r.id AND r.propietario.id = :id AND rc.cancion.id = :cancionid ";
            
            javax.persistence.Query query = getEntityManager().createQuery(querystr, Recocancion.class);
            
            query.setParameter("id", personaId);
            query.setParameter("cancionid", cancionId);

//            List<Recocancion> canciones =  query.getResultList();
//
//            for (Recocancion rc : canciones) {
//                recos.add(rc.getRecopilacion());
//            }
            
            recos = query.getResultList();
//            session.close();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        return recos;
    }
    
    public List<Recopilacion> getRecopilaciones(long personaId) {

        List<Recopilacion> recos = new ArrayList<>();
        try {
            String querystr = "SELECT r FROM Recopilacion r "
                    + "WHERE r.propietario.id = :id";
            
            javax.persistence.Query query = getEntityManager().createQuery(querystr, Recocancion.class);
            
            query.setParameter("id", personaId);
            recos = query.getResultList();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        return recos;
    }
    
    public List<Recopilacion> getRecopilacionesPublicas() {

        List<Recopilacion> recos = new ArrayList<>();
        try {
            String querystr = "SELECT r FROM Recopilacion r WHERE r.publica = 1";
            
            javax.persistence.Query query = getEntityManager().createQuery(querystr, Recocancion.class);
            
            recos = query.getResultList();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        return recos;
    }
    
}
