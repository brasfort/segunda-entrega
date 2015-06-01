/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Persona;
import edu.uniminuto.sesion.PersonaFacade;
import edu.uniminuto.sesion.RolFacade;
import java.io.IOException;
import java.util.Date;
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
@WebServlet(name = "Registro", urlPatterns = {"/registro"})
public class Registro extends HttpServlet {
    @EJB
    private PersonaFacade personaFacade;
    @EJB
    private RolFacade rolFacade;

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

        Persona persona = new Persona();
                
        
        java.text.SimpleDateFormat sdt = new java.text.SimpleDateFormat("yyyy-MM-dd");

        boolean administrador = false;
        if (request.getSession().getAttribute("usuario") != null) {
            persona = (edu.uniminuto.entidades.Persona) request.getSession().getAttribute("usuario");
            administrador = persona.getRol().getId() == 3;
        }

        if (request.getParameter("nombres") != null) {
            persona.setNombres(request.getParameter("nombres"));
        }

        if (request.getParameter("apellidos") != null) {
            persona.setApellidos(request.getParameter("apellidos"));
        }

        if (request.getParameter("nacimiento") != null) {

            try {

                persona.setNacimiento(
                        sdt.parse(request.getParameter("nacimiento")));
            } catch (Exception ex) {

            }
        }

        if (request.getParameter("correo") != null) {
            persona.setCorreo(request.getParameter("correo"));
        }

        if (request.getParameter("rol") != null) {
            int rol = 1;

            if (request.getParameter("rol").equals("vendedor")) {
                rol = 2;
            } else if (request.getParameter("rol").equals("administrador")) {
                rol = 3;
            }
            persona.setRol(rolFacade.find(rol));
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/registro.jsp");
        request.setAttribute("persona", persona);
        request.setAttribute("admin", administrador);
        request.setAttribute("nacimiento", (persona.getNacimiento() != null) ? sdt.format(persona.getNacimiento()) : null);
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
        
        
        String url = "registro.jsp";
        Persona persona = new Persona();

        boolean logeado = request.getSession().getAttribute("usuario") != null;
        if (logeado) {
            persona = (Persona) request.getSession().getAttribute("usuario");
        }

        java.text.SimpleDateFormat sdt = new java.text.SimpleDateFormat("yyyy-MM-dd");

        String nombres = getParameter(request, "nombres");
        String apellidos = getParameter(request, "apellidos");
        Date nacimiento = new Date(0);
        try {
            nacimiento = sdt.parse(getParameter(request, "nacimiento"));
        } catch (Exception ex) {

        }
        String correo = getParameter(request, "correo");
        String clave = getParameter(request, "clave");

        if (!nombres.isEmpty()) {
            persona.setNombres(nombres);
        }
        if (!apellidos.isEmpty()) {
            persona.setApellidos(apellidos);
        }
        if (nacimiento.getTime() != (new Date()).getTime()) {
            persona.setNacimiento(nacimiento);
        }
        if (!correo.isEmpty()) {
            persona.setCorreo(correo);
        }
        if (!clave.isEmpty()) {
            persona.setClave(clave);
        }

        if (!logeado) {
            String rol = getParameter(request, "rol");
            
            short rolId = 1;
            if (rol.equals("vendedor")) {
                rolId = 2;
            }
            persona.setRol(rolFacade.find(rolId));
        }
        
        
        personaFacade.create(persona);
        if (persona.getId() != null) {
            if (logeado) {
                url = "index.jsp";
            } else {
                url = "entrar.jsp?correo=" + correo;
            }
        } else {
            url = url + "?nombres=" + nombres
                    + "&apellidos=" + apellidos
                    + "&nacimiento=" + sdt.format(nacimiento)
                    + "&correo=" + correo
                    + "&error=Hubo un fallo al momento de guardar los datos";
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

    private String getParameter(
            HttpServletRequest request, String parametername) {
        String ret = "";
        if (request.getParameter(parametername) != null) {
            ret = request.getParameter(parametername);
        }
        return ret;
    }// </editor-fold>

}
