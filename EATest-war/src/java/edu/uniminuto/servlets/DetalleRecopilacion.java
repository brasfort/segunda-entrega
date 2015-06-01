/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Recopilacion;
import edu.uniminuto.sesion.RecocancionFacade;
import edu.uniminuto.sesion.RecopilacionFacade;
import java.io.IOException;
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
@WebServlet(name = "DetalleRecopilacion", urlPatterns = {"/recopilacion"})
public class DetalleRecopilacion extends HttpServlet {
    @EJB
    private RecopilacionFacade recopilacionFacade;
    @EJB
    private RecocancionFacade recoCancionFacade;

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

        int id = (request.getParameter("id") != null) ? Integer.valueOf(request.getParameter("id")) : -1;
        if (accion.equals("borrarc")) {
            int rel = (request.getParameter("relacion") != null) ? Integer.valueOf(request.getParameter("relacion")) : -1;

            if (rel > 0) {
                recoCancionFacade.remove(recoCancionFacade.find(rel));
                
            }
        } else if (accion.equals("borrar")) {
            Recopilacion r = recopilacionFacade.find(id);
            
            recopilacionFacade.remove(r);
            
            response.sendRedirect("recopilaciones");
            return;
        }

        long uid
                = (request.getSession().getAttribute("usuario") != null)
                        ? ((edu.uniminuto.entidades.Persona) request.getSession().getAttribute("usuario")).getId() : -1;

        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/recopilacion.jsp");
        request.setAttribute("reco", recopilacionFacade.find(id));
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
