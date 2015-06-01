/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Persona;
import edu.uniminuto.sesion.CancionFacade;
import edu.uniminuto.sesion.PersonaFacade;
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
 * @author jhon
 */
@WebServlet(name = "IniciodeSesion", urlPatterns = {"/entrar"})
public class IniciodeSesion extends HttpServlet {

    @EJB
    private PersonaFacade personaFacade;
    @EJB
    private CancionFacade cancionFacade;

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

        String url = "/index";
        String correo = getParameter(request, "correo");
        String clave = getParameter(request, "clave");

        if (correo.isEmpty()) {
            url = "/entrar.jsp?correo=" + correo + "&error=El correo no puede estar en blanco";
        } else if (clave.isEmpty()) {
            url = "/entrar.jsp?correo=" + correo + "&error=La clave no puede estar en blanco";
        } else {
            Persona usuario = personaFacade.buscarIniciodeSesion(correo, clave);

            if (usuario != null && usuario.getId() != null) {
                request.getSession().setAttribute("usuario", usuario);
                
                request.setAttribute("canciones", cancionFacade.getCanciones());
                url = "/canciones";
                
            } else {
                url = "/entrar.jsp?correo=" + correo + "&error=Los datos no coinciden, por favor, ingrese un correo y una clave validos";
            }
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        request.setAttribute("isget", true);
        rd.forward(request, response);
        
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
        String ret = "";
        if (request.getParameter(parametername) != null) {
            ret = request.getParameter(parametername);
        }
        return ret;
    }// </editor-fold>

}
