/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Disco;
import edu.uniminuto.sesion.DiscoFacade;
import edu.uniminuto.sesion.GeneroFacade;
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
@WebServlet(name = "FormularioDisco", urlPatterns = {"/fdisco"})
public class FormularioDisco extends HttpServlet {
    @EJB
    private DiscoFacade discoFacade;
    @EJB
    private GeneroFacade generoFacade;
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

        boolean editando = false;

        
        Disco disco = new Disco();
        if (request.getParameter("id") != null) {

            int id = Integer.valueOf(request.getParameter("id"));
            disco = discoFacade.find(id);
            editando = true;
        }

        if (request.getParameter("nombre") != null) {
            disco.setNombre(request.getParameter("nombre"));
        }

        if (request.getParameter("anhio") != null) {
            java.util.Calendar c = java.util.Calendar.getInstance();
            c.set(Integer.valueOf(request.getParameter("anhio")), 0, 0, 0, 0, 0);
            disco.setAnhio(c.getTime());
        }

        if (request.getParameter("genero") != null) {
            disco.setGenero(generoFacade.find(Short.valueOf(request.getParameter("genero"))));
        }
        if (request.getParameter("interprete") != null) {
            disco.setInterprete(interpreteFacade.find(Integer.valueOf(request.getParameter("interprete"))));

        }

        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/fdisco.jsp");
        request.setAttribute("disco", disco);
        request.setAttribute("precio", (request.getParameter("precio") != null) ? request.getParameter("precio") : 0);
        request.setAttribute("editando", editando);
        request.setAttribute("generos", generoFacade.findAll());
        request.setAttribute("interpretes", interpreteFacade.findAll());
        
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
