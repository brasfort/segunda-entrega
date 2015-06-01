/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.middle;

import edu.uniminuto.entidades.Disco;
import edu.uniminuto.sesion.DiscoFacade;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Jhon Gómez
 */
public class DFLayer {
    @EJB
    private DiscoFacade discoFacade;
    
    public List<Disco> getDiscos(String letra) {
        return discoFacade.buscarDiscosporInicial(letra);
    }
}
