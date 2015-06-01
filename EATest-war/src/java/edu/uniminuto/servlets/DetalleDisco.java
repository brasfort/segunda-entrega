/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Disco;
import edu.uniminuto.entidades.Discopropietario;
import edu.uniminuto.sesion.DiscoFacade;
import edu.uniminuto.sesion.DiscopropietarioFacade;
import java.io.IOException;
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
@WebServlet(name = "DetalleDisco", urlPatterns = {"/disco"})
public class DetalleDisco extends HttpServlet {
    @EJB
    private DiscoFacade discoFacade;
    @EJB
    private DiscopropietarioFacade discoPropietarioFacade;
    
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

        String id = request.getParameter("id");
        
        Disco disco = discoFacade.find(Integer.valueOf(id));

        long idSesion = -1;
        if (request.getSession().getAttribute("usuario") != null) {
            idSesion = ((edu.uniminuto.entidades.Persona) request.getSession().getAttribute("usuario")).getId();
        }

        List<Discopropietario> dps = discoPropietarioFacade.buscarDiscos(Integer.valueOf(id));
          
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/disco.jsp");
        
        request.setAttribute("disco", disco);
        request.setAttribute("discopropietarioList", dps);
        request.setAttribute("idsesion", idSesion);
        
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
    }// </editor-fold>

}
