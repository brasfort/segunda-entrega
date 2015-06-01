/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Cancion;
import edu.uniminuto.entidades.Discocancion;
import edu.uniminuto.sesion.CancionFacade;
import edu.uniminuto.sesion.DiscoFacade;
import edu.uniminuto.sesion.DiscocancionFacade;
import edu.uniminuto.sesion.InterpreteFacade;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "AdministrarCancion", urlPatterns = {"/admcancion"})
public class AdministrarCancion extends HttpServlet {

    @EJB
    DiscoFacade discoFacade;
    
    @EJB
    DiscocancionFacade discoCancionFacade;
    
    @EJB
    CancionFacade cancionFacade;
    @EJB
    InterpreteFacade interpreteFacade;

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

        List<edu.uniminuto.entidades.Disco> discos = new ArrayList<>();
        List<edu.uniminuto.entidades.Cancion> canciones = null;

        if (getParameter(request, "accion").equals("agregar")) {

            long idCancion = Long.valueOf(getParameter(request, "cancion"));
            int vinilo = Integer.valueOf(getParameter(request, "disco"));

            Discocancion discoCancion = new Discocancion();
            discoCancion.setCancion(cancionFacade.find(idCancion));
            discoCancion.setDisco(discoFacade.find(vinilo));
            
            discoCancionFacade.create(discoCancion);
        }

        
        canciones = cancionFacade.getCanciones(getParameter(request, "qu"));
        edu.uniminuto.entidades.Persona p;
        if (request.getSession().getAttribute("usuario") != null) {

            discos = discoFacade.getListadeDiscos(((edu.uniminuto.entidades.Persona) request.getSession().getAttribute("usuario")).getId());
        }

        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/agregarcancion.jsp");
        
        request.setAttribute("mensaje", request.getParameter("mensaje"));
        request.setAttribute("qu", request.getParameter("qu"));
        request.setAttribute("canciones", canciones);
        request.setAttribute("discos", discos);
        request.setAttribute("relacionado", false);
        
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

        String url = "entrar.jsp";
        if (request.getSession().getAttribute("usuario") != null) {

            String accion = getParameter(request, "accion");
            Cancion cancion = new Cancion();

            java.text.SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

            if (accion.equals("editar")) {
                long id = Long.valueOf(getParameter(request, "id"));
                cancion = cancionFacade.find(id);
            }

            if (!getParameter(request, "nombre").isEmpty()) {
                cancion.setNombre(getParameter(request, "nombre"));
            }

            if (!getParameter(request, "anhio").isEmpty()) {
                cancion.setAnhio(getParameter(request, "anhio"));
            }

            if (!getParameter(request, "duracion").isEmpty()) {

                try {
                    cancion.setDuracion(sdf.parse(getParameter(request, "duracion")));
                } catch (Exception ex) {
                    java.util.Calendar c = java.util.Calendar.getInstance();
                    c.set(java.util.Calendar.HOUR_OF_DAY, 0);
                    c.set(java.util.Calendar.MINUTE, 4);

                    cancion.setDuracion(c.getTime());
                }
            }

            if (!getParameter(request, "peso").isEmpty()) {
                cancion.setPeso(getParameter(request, "peso"));
            }

            if (!getParameter(request, "calidad").isEmpty()) {

                cancion.setCalidad(getParameter(request, "calidad"));
            }

            if (!getParameter(request, "precio").isEmpty()) {
                cancion.setPrecio(Integer.valueOf(getParameter(request, "precio")));
            }

            if (!getParameter(request, "interprete").isEmpty()) {

                cancion.setInterprete(interpreteFacade.find(
                        Integer.valueOf(getParameter(request, "interprete"))));
            }

            cancionFacade.create(cancion);

            if (cancion.getId() != null) {
                url = "cancion?id=" + cancion.getId();
            } else {
                url = url + "fcancion?nombre=" + getParameter(request, "cancion")
                        + "&anhio=" + getParameter(request, "anhio")
                        + "&peso=" + getParameter(request, "peso")
                        + "&calidad=" + getParameter(request, "calidad")
                        + "&precio=" + getParameter(request, "precio")
                        + "&interprete=" + getParameter(request, "interprete")
                        + "&error=Hubo un fallo al momento de guardar los datos";

                if (accion.equals("editar")) {
                    url = url + "&id=" + cancion.getId();
                }

                try {
                    url = url + "&duracion=" + sdf.parse(getParameter(request, "duracion"));
                } catch (Exception ex) {

                    java.util.Calendar c = java.util.Calendar.getInstance();
                    c.set(java.util.Calendar.HOUR_OF_DAY, 0);
                    c.set(java.util.Calendar.MINUTE, 4);

                    url = url + "&duracion=" + sdf.format(c.getTime());
                }
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

    private String getParameter(
            HttpServletRequest request, String parametername) {
        String ret = "";
        if (request.getParameter(parametername) != null) {
            ret = request.getParameter(parametername);
        }
        return ret;
    }// </editor-fold>

}
