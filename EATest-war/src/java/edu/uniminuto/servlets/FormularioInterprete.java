/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Interprete;
import edu.uniminuto.sesion.InterpreteFacade;
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
@WebServlet(name = "FormularioInterprete", urlPatterns = {"/finterprete"})
public class FormularioInterprete extends HttpServlet {
    @EJB
    private InterpreteFacade interpreteFacade;

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

        String accion = "crear";
        String id = (request.getParameter("id") != null)
                ? request.getParameter("id") : "";

        String nombre = (request.getParameter("nombre") != null)
                ? request.getParameter("nombre") : "";

        
        Interprete interprete = new Interprete();
        interprete.setNombre(nombre);
        
        if (!id.isEmpty() && !id.equals("null")) {
            accion = "editar";
            
            interprete = interpreteFacade.find(Integer.valueOf(id));
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/finterprete.jsp");

        request.setAttribute("interprete", interprete);
        request.setAttribute("accion", accion);
        request.setAttribute("error", request.getParameter("error"));
        
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
