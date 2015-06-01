/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Recopilacion;
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
@WebServlet(name = "ListadodeRecopilaciones", urlPatterns = {"/recopilaciones"})
public class ListadodeRecopilaciones extends HttpServlet {
    
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

        String accion = (request.getParameter("accion") != null) ? request.getParameter("accion") : "";
        long uid = -1;

        if (request.getSession().getAttribute("usuario") != null) {
            uid = ((edu.uniminuto.entidades.Persona) request.getSession().getAttribute("usuario")).getId();
        }
        if (accion.equals("borrar") && request.getSession().getAttribute("usuario") != null) {
            int id = (request.getParameter("id") != null) ? Integer.valueOf(request.getParameter("id")) : -1;

            if (id > 0) {
                
                Recopilacion recopilacion  = recopilacionFacade.find(id);
                
                if (recopilacion != null && recopilacion.getPropietario().getId() == uid) {
                    recopilacionFacade.remove(recopilacion);
                }
            }
        }

        List<Recopilacion> recopilaciones;
        
        if (request.getParameter("propias") != null && uid != -1) {
            recopilaciones = recopilacionFacade.getRecopilaciones(uid);
        } else {
            recopilaciones = recopilacionFacade.getRecopilacionesPublicas();
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/recopilaciones.jsp");
        request.setAttribute("recos", recopilaciones);
        request.setAttribute("uid", uid);
        request.setAttribute("mensaje", request.getParameter("mensaje"));
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
