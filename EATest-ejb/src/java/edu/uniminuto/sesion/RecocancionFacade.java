/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.sesion;

import edu.uniminuto.entidades.Recocancion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jhon Gómez
 */
@Stateless
public class RecocancionFacade extends AbstractFacade<Recocancion> {
    @PersistenceContext(unitName = "EATest-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    private static final int MAX_RESULTADOS = 20;

    public RecocancionFacade() {
        super(Recocancion.class);
    }
    

    public Recocancion getRecoCancion(long cancion, long lista) {
        
        try {
            String querystr = "SELECT r FROM Recocancion r WHERE r.recopilacion.id = :recopilacion AND r.cancion.id = :cancion";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Recocancion.class);
            
            
            q.setParameter("recopilacion", lista);
            q.setParameter("cancion", cancion);

            return (Recocancion)q.getSingleResult();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        return null;
    }
    
}
