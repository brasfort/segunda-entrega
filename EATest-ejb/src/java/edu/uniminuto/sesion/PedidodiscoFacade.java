/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.sesion;

import edu.uniminuto.entidades.Pedidodisco;
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
public class PedidodiscoFacade extends AbstractFacade<Pedidodisco> {
    @PersistenceContext(unitName = "EATest-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidodiscoFacade() {
        super(Pedidodisco.class);
    }
    
    
    
    
    public List<Pedidodisco> getSolicitudes(long id, int discoId) {
        List<Pedidodisco> pedidoDiscos = new ArrayList<>();
        
       
        try {
            String querystr = "SELECT pd FROM Pedidodisco pd, Discopropietario dp WHERE pd.discopropietario.id = dp.id AND "
                    + "enviado = 0 AND rechazado = 0 AND dp.persona.id = :id AND dp.disco.id = :discoId";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Pedidodisco.class);
           
            
            q.setParameter("id", id);
            q.setParameter("discoId", discoId);

            pedidoDiscos = q.getResultList();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        
        
        return pedidoDiscos;
    }
    
    
    
    
    public List<Pedidodisco> getSolicitudesporPropiedad(int discopId) {
        List<Pedidodisco> pedidoDiscos = new ArrayList<>();
        
       
        try {
            String querystr = "SELECT pd FROM Pedidodisco pd WHERE pd.discop.id = :relacion AND pd.enviado = 0 AND pd.rechazado = 0";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Pedidodisco.class);
           
            q.setParameter("relacion", discopId);

            pedidoDiscos = q.getResultList();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        
        
        return pedidoDiscos;
    }
    
    
    public List<Pedidodisco> getSolicitudes(long personaId) {
        List<Pedidodisco> pedidoDiscos = new ArrayList<>();
        
       
        try {
            String querystr = "SELECT pd FROM Pedidodisco pd, Discopropietario dp WHERE dp.vendido = 0 AND pd.discop.id = dp.id AND pd.enviado = 0 AND pd.rechazado = 0 AND dp.propietario.id = :personaId";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Pedidodisco.class);
           
            
            q.setParameter("personaId", personaId);

            pedidoDiscos = q.getResultList();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        
        
        return pedidoDiscos;
    }	
    
    
    public List<Pedidodisco> getSolicitudesporPersona(long personaId, int pedidoId, int discoId) {
        List<Pedidodisco> pedidoDiscos = new ArrayList<>();
        
       
        try {
            String querystr = "SELECT pd FROM Pedidodisco pd, Discopropietario dp WHERE dp.vendido = 0 AND pd.discop.id = dp.id AND pd.enviado = 0 AND pd.rechazado = 0 AND dp.propietario.id = :personaId AND pd.id != :pedidoId AND dp.disco.id = :discoId";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Pedidodisco.class);
           
            
            q.setParameter("personaId", personaId);
            q.setParameter("pedidoId", pedidoId);
            q.setParameter("discoId", discoId);

            pedidoDiscos = q.getResultList();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        
        
        return pedidoDiscos;
    }	
}
