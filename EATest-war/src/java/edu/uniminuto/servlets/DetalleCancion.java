/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Cancion;
import edu.uniminuto.entidades.Disco;
import edu.uniminuto.entidades.Discocancion;
import edu.uniminuto.entidades.Recopilacion;
import edu.uniminuto.sesion.CancionFacade;
import edu.uniminuto.sesion.DiscoFacade;
import edu.uniminuto.sesion.DiscocancionFacade;
import edu.uniminuto.sesion.RecopilacionFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jhon Gómez
 */
@WebServlet(name = "DetalleCancion", urlPatterns = {"/cancion"})
public class DetalleCancion extends HttpServlet {
    @EJB
    private CancionFacade cancionFacade;
    @EJB
    private DiscoFacade discoFacade;
    @EJB
    private DiscocancionFacade discoCancionFacade;
    @EJB
    private RecopilacionFacade recopilacionFacade;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = getParameter(request, "id");

        
        
        
        Cancion cancion = cancionFacade.find(Long.valueOf(id));

        
        if (getParameter(request, "accion").equals("agregar")) {

            int vinilo = Integer.valueOf(getParameter(request, "disco"));

            Disco disco = discoFacade.find(vinilo);
            
            
            Discocancion discocancion = new Discocancion();
            discocancion.setCancion(cancion);
            discocancion.setDisco(disco);
            
            cancion.getDiscocancionList().add(discocancion);
            
            cancionFacade.edit(cancion);
        }

       

        edu.uniminuto.entidades.Persona p
                = (request.getSession().getAttribute("usuario") != null)
                        ? ((edu.uniminuto.entidades.Persona) request.getSession().getAttribute("usuario")) : null;

        List<Recopilacion> recos
                = (p != null) ? recopilacionFacade.getRecopilaciones(p.getId())
                : new ArrayList<Recopilacion>();


        List<Recopilacion> relacionadas
                = (p != null) 
                ? recopilacionFacade.getRecopilacionesAgregadas(p.getId(), cancion.getId()) 
                : new ArrayList<Recopilacion>();

        for (edu.uniminuto.entidades.Recopilacion re : relacionadas) {
            for (edu.uniminuto.entidades.Recopilacion re2 : recos) {
                if (re2.getId() == re.getId()) {
                    recos.remove(re2);
                    break;
                }
            }
        }
//    recos.removeAll(relacionadas);

        List<Disco> discos = (p != null)
                ? discoFacade.getListadeDiscos(((edu.uniminuto.entidades.Persona) request.getSession().getAttribute("usuario")).getId())
                : new ArrayList<Disco>();
            
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/cancion.jsp");
        
        
        
        request.setAttribute("discos", discos);
        request.setAttribute("cancion", cancion);
        request.setAttribute("accion", request.getParameter("accion"));
        request.setAttribute("recos", recos);
        request.setAttribute("rels", relacionadas);
        
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     * 
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private String getParameter(
            HttpServletRequest request, String parametername) {
        String ret = "0";
        if (request.getParameter(parametername) != null) {
            ret = request.getParameter(parametername);
        }
        return ret;
    }// </editor-fold>

}
