/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.sesion;

import edu.uniminuto.entidades.Persona;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.validator.HibernateValidator;

/**
 *
 * @author Jhon Gómez
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "EATest-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    public Persona buscarIniciodeSesion(String correo, String clave) {
        try {

            //"FROM Persona p WHERE p.correo = :correo AND p.clave := clave", 
            String querystr = "SELECT p FROM Persona p WHERE p.correo = :correo and p.clave = :clave";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Persona.class);

            q.setParameter("correo", correo);
            q.setParameter("clave", clave);

            return (Persona) q.getSingleResult();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        return null;
    }
}
