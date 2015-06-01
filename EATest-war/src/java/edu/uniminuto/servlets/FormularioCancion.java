/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Cancion;
import edu.uniminuto.sesion.CancionFacade;
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
@WebServlet(name = "FormularioCancion", urlPatterns = {"/fcancion"})
public class FormularioCancion extends HttpServlet {

    @EJB
    private CancionFacade cancionFacade;
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

        
        Cancion cancion = new Cancion();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
        if (request.getParameter("id") != null) {
            String id = request.getParameter("id");
            cancion = cancionFacade.find(Long.valueOf(id));
        }

        if (request.getParameter("nombre") != null) {
            cancion.setNombre(request.getParameter("nombre"));
        }

        if (request.getParameter("anhio") != null) {
            cancion.setAnhio(request.getParameter("anhio"));
        }

        if (request.getParameter("duracion") != null) {

            try {
                cancion.setDuracion(sdf.parse(request.getParameter("duracion")));
            } catch (Exception ex) {

            }
        }

        if (request.getParameter("peso") != null) {
            cancion.setPeso(request.getParameter("peso"));
        }

        if (request.getParameter("calidad") != null) {

            cancion.setCalidad(request.getParameter("calidad"));
        }

        if (request.getParameter("precio") != null) {
            cancion.setPrecio(Integer.valueOf(request.getParameter("precio")));
        }

        if (request.getParameter("interprete") != null) {

            cancion.setInterprete(
                    interpreteFacade.find(Integer.valueOf(request.getParameter("interprete"))));
        }

        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/fcancion.jsp");
        request.setAttribute("cancion", cancion);
        request.setAttribute("interpretes", interpreteFacade.findAll());
        request.setAttribute("accion", request.getParameter("accion"));
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
