/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.sesion;

import edu.uniminuto.entidades.Discopropietario;
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
public class DiscopropietarioFacade extends AbstractFacade<Discopropietario> {
    @PersistenceContext(unitName = "EATest-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiscopropietarioFacade() {
        super(Discopropietario.class);
    }
    
    

    public List<Discopropietario> buscarDiscos(int discoId) {
        List<Discopropietario> dps = new ArrayList<>();
        try {

            String querystr = "SELECT dp FROM Discopropietario dp WHERE dp.vendido = 0 AND dp.disco.id = :discoId";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Discopropietario.class);

            q.setParameter("discoId", discoId);

            dps = q.getResultList();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        return dps;
    }
    
}
