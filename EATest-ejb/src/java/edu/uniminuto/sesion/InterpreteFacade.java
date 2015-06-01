/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.sesion;

import edu.uniminuto.entidades.Interprete;
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
public class InterpreteFacade extends AbstractFacade<Interprete> {
    @PersistenceContext(unitName = "EATest-ejbPU")
    private EntityManager em;
    public final static int MAX_RESULTADOS = 20;
    private String error;

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InterpreteFacade() {
        super(Interprete.class);
    }

    public List<Interprete> getInterpretes(long ultimoId) {
        List<Interprete> interpretes = new ArrayList<>();
        try {
            String querystr = "SELECT i FROM Interprete i WHERE i.id > :id ORDER BY id";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Interprete.class);
            q.setMaxResults(MAX_RESULTADOS);
            
            q.setParameter("id", ultimoId);

            interpretes = q.getResultList();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        return interpretes;
    }
    
    
    
    
    public boolean isValid(Interprete interprete) {
        boolean valido = true;
        
        if (interprete.getNombre() == null || interprete.getNombre().isEmpty()) {
            setError("El nombre no puede estar vacío");
            valido = false;
        }
        return valido;
    }
}
