/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.sesion;

import edu.uniminuto.entidades.Genero;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jhon Gómez
 */
@Stateless
public class GeneroFacade extends AbstractFacade<Genero> {
    @PersistenceContext(unitName = "EATest-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GeneroFacade() {
        super(Genero.class);
    }
    
}
