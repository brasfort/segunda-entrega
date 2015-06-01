/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Compradisco;
import static edu.uniminuto.entidades.Pedidocancion_.cancion;
import edu.uniminuto.sesion.CompraFacade;
import edu.uniminuto.sesion.CompradiscoFacade;
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
@WebServlet(name = "ListadodeCompras", urlPatterns = {"/compras"})
public class ListadodeCompras extends HttpServlet {
    @EJB
    private CompraFacade compraFacade;
    
    @EJB
    private CompradiscoFacade compraDiscoFacade;

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
        
        java.util.List<edu.uniminuto.entidades.Compra> compras = 
                new java.util.ArrayList<edu.uniminuto.entidades.Compra>();

        if (request.getSession().getAttribute("usuario") != null) {
            compras = compraFacade.getComprasporComprador(((edu.uniminuto.entidades.Persona)request.getSession().getAttribute("usuario")).getId());
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/compras.jsp");
        
        request.setAttribute("compras", compras);
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
        
        
        if (request.getParameter("cdid") != null) {
            int cdid = Integer.valueOf(request.getParameter("cdid"));
            int calificacion = Integer.valueOf(request.getParameter("calificacion"));
        
            Compradisco cd = compraDiscoFacade.find(cdid);
            cd.setCalificacion(calificacion);
            
            
            compraDiscoFacade.edit(cd);
        }
        
        doGet(request, response);
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
