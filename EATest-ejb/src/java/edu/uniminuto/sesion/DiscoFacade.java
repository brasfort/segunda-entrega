/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.sesion;

import edu.uniminuto.entidades.Disco;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DiscoFacade extends AbstractFacade<Disco> {
    @PersistenceContext(unitName = "EATest-ejbPU")
    private EntityManager em;

    private static final int MAX_RESULTADOS = 20;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiscoFacade() {
        super(Disco.class);
    }

    public List<Disco> buscarDiscosporInicial(String letra) {
        List<Disco> discos = new ArrayList<>();
        try {

            String querystr = "SELECT d FROM Disco d WHERE d.nombre like :letra";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Disco.class);

            q.setParameter("letra", letra + "%");

            discos = q.getResultList();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        return discos;
    }
    
    

    public List<Disco> buscarDiscos(int ultimoId) {
        List<Disco> discos = new ArrayList<>();
        try {

            String querystr = "SELECT d FROM Disco d ORDER BY d.id";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Disco.class);
            q.setFirstResult(ultimoId);
            q.setMaxResults(MAX_RESULTADOS);

            discos = q.getResultList();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        return discos;
    }

    public List<Disco> buscarDiscos() {
        return buscarDiscos(0);
    }

    public List<Disco> getDiscos(String letra, long ultimoId) {
        List<Disco> discos = new ArrayList<>();
        try {
            String querystr = "SELECT d FROM Disco d WHERE d.nombre like :letra AND d.id > :id";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Disco.class);
            q.setMaxResults(MAX_RESULTADOS);
            
            q.setParameter("letra", letra + "%");
            q.setParameter("id", ultimoId);

            discos = q.getResultList();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        return discos;
    }
    

    public List<Disco> getListadeDiscos(long userId) {
        List<Disco> discos = new ArrayList<>();
        try {
            String querystr = "SELECT DISTINCT d FROM Disco d, Discopropietario dp WHERE d.id = dp.disco.id AND dp.propietario.id = :personaId";

            javax.persistence.Query q = getEntityManager().createQuery(querystr, Disco.class);
            
            q.setParameter("personaId", userId);

            discos = q.getResultList();
        } catch (Exception ex) {
            String exdf = ex.getMessage();
        }
        return discos;
    }
}
