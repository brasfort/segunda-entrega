/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.sesion;

import edu.uniminuto.entidades.Compra;
import edu.uniminuto.entidades.Compracancion;
import edu.uniminuto.entidades.Compradisco;
import edu.uniminuto.entidades.Pedido;
import edu.uniminuto.entidades.Pedidocancion;
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
public class CompraFacade extends AbstractFacade<Compra> {
    @PersistenceContext(unitName = "EATest-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }
    
    
    
    public Compra hacerCompra(Pedido pedido) {
        
        long total = 0;
        String comentarios = "";
        for (Pedidodisco pedidodisco : pedido.getPedidodiscoList()) {
            if (pedidodisco.getEnviado() == true
                    && pedidodisco.getRechazado() == false) {
                total = total + pedidodisco.getDiscop().getPrecio();
            }
            
            if (pedidodisco.getRechazado() == true) {
                comentarios = comentarios + "<br />El disco " + pedidodisco.getDiscop().getDisco().getNombre() + " fue rechazado. <br /> " + pedidodisco.getComentario();
            }
        }

        for (Pedidocancion pedidocancion : pedido.getPedidocancionList()) {
            total = total + pedidocancion.getCancion().getPrecio();
        }
        
        Compra compra = new Compra();
        compra.setComprador(pedido.getComprador());
        compra.setFecha(pedido.getFechapedido());
        compra.setTotal(total);
        compra.setObservaciones(comentarios);
        
        
        create(compra);
        return compra;
        

    }
    
    public List<Compra> getComprasporComprador(long compradorId) {
        List<Compra> compras = new ArrayList<>();
        
        try {
            String querystr = "SELECT c FROM Compra c WHERE c.comprador.id = :compradorId";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Compra.class);
            
            
            q.setParameter("compradorId", compradorId);

            compras = q.getResultList();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        
        return compras;
    }
    
}
