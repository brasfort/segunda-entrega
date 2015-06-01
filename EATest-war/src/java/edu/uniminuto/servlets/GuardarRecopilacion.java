/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Persona;
import edu.uniminuto.entidades.Recopilacion;
import edu.uniminuto.sesion.RecopilacionFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jhon Gómez
 */
@WebServlet(name = "GuardarRecopilacion", urlPatterns = {"/guardarrecopilacion"})
public class GuardarRecopilacion extends HttpServlet {
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

        String url = "recopilaciones";
        if (request.getSession().getAttribute("usuario") == null) {
            url = "entrar.jsp?error=Debe haber iniciado sesion";
        } else {

            Persona usuario = (Persona) request.getSession().getAttribute("usuario");
            
            String nombre = getParameter(request, "nombre");
            String accion = getParameter(request, "accion");
            boolean publica = request.getParameter("publica") != null;

            Recopilacion recopilacion;

            if (accion.equals("crear")) {
                recopilacion = new edu.uniminuto.entidades.Recopilacion();
                
                recopilacion.setNombre(nombre);
                recopilacion.setFecha(new java.util.Date());
                recopilacion.setPublica(publica);
                recopilacion.setPropietario(usuario);
                
                recopilacionFacade.create(recopilacion);
                
                
            } else {
                int id = Integer.valueOf(getParameter(request, "id"));
                recopilacion = recopilacionFacade.find(id);

                recopilacion.setNombre(nombre);
                recopilacion.setPublica(publica);
                recopilacionFacade.edit(recopilacion);
            }
            if (recopilacion.getId() != null) {
                url = "recopilacion?id=" + recopilacion.getId();
            }
        }
        response.sendRedirect(url);
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

    private String getParameter(HttpServletRequest request, String parametername) {
        String ret = "";
        if (request.getParameter(parametername) != null) {
            ret = request.getParameter(parametername);
        }
        return ret;
    }// </editor-fold>

}
