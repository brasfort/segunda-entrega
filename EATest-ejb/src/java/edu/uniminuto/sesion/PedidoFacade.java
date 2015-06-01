/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.sesion;

import edu.uniminuto.entidades.Pedido;
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
public class PedidoFacade extends AbstractFacade<Pedido> {
    @PersistenceContext(unitName = "EATest-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }
    
    
    public List<Pedido> getPedidorporComprador(long compradorId) {
        List<Pedido> pedidos = new ArrayList<>();
        
       
        
        try {
            String querystr = "FROM Pedido p WHERE p.descartado = 0 AND p.comprador.id = :compradorId";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Pedido.class);
            
            q.setParameter("compradorId", compradorId);

            pedidos = q.getResultList();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        
        return pedidos;
    }
    
    
}
