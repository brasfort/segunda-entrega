/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Persona;
import edu.uniminuto.entidades.Recocancion;
import edu.uniminuto.entidades.Recopilacion;
import edu.uniminuto.sesion.CancionFacade;
import edu.uniminuto.sesion.RecocancionFacade;
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
@WebServlet(name = "ProcesarCancionRecopilacion", urlPatterns = {"/cancionreco"})
public class ProcesarCancionRecopilacion extends HttpServlet {

    @EJB
    private RecopilacionFacade recopilacionFacade;
    @EJB
    private CancionFacade cancionFacade;

    @EJB
    private RecocancionFacade recocancionFacade;

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

        String url = "entrar.jsp";
        if (request.getSession().getAttribute("usuario") != null) {

            String operacion = getParameter(request, "accion");
            if (operacion.equals("agregar")) {
                int lista = Integer.valueOf(getParameter(request, "recopilacion"));
                long cancion = Long.valueOf(getParameter(request, "cancion"));
                url = "cancion?id=" + cancion;

                Recocancion recopilacionCancion = new Recocancion();

                recopilacionCancion.setCancion(cancionFacade.find(cancion));
                recopilacionCancion.setRecopilacion(recopilacionFacade.find(lista));

                Recopilacion recopilacion = recopilacionFacade.find(lista);
                recopilacion.getRecocancionList().add(recopilacionCancion);

                recopilacionFacade.edit(recopilacion);

            } else if (operacion.equals("borrarc")) {

                int lista = Integer.valueOf(getParameter(request, "recopilacion"));
                Recopilacion recopilacion = recopilacionFacade.find(lista);

                url = "recopilacion?id=" + lista;
                
                if (request.getParameter("relacion") != null) {
                    int relacion = Integer.valueOf(getParameter(request, "relacion"));

                    for (Recocancion recoCancion : recopilacion.getRecocancionList()) {
                        if (recoCancion.getId() == relacion) {

                            recopilacion.getRecocancionList().remove(recoCancion);
                            recocancionFacade.remove(recoCancion);
                            break;
                        }
                    }

                    recopilacionFacade.edit(recopilacion);
                } else {

                    int cancionId = Integer.valueOf(getParameter(request, "cancion"));

                    Recocancion recoCancion = recocancionFacade.getRecoCancion(cancionId, lista);

                    recopilacion.getRecocancionList().remove(recoCancion);
                    recopilacionFacade.edit(recopilacion);

                    recocancionFacade.remove(recoCancion);

                    if (request.getParameter("vista") != null && request.getParameter("vista").equals("cancion")) {
                        url = "cancion?id=" + cancionId;
                    } 
                }
            }
        }

        response.sendRedirect(url);
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

            int lista = Integer.valueOf(getParameter(request, "recopilacion"));
            String nombre = getParameter(request, "nombre");

            Persona persona = (Persona) request.getSession().getAttribute("usuario");

            Recopilacion recopilacion = new Recopilacion();
            recopilacion.setPropietario(persona);
            recopilacion.setNombre(nombre);
            recopilacion.setFecha(new java.util.Date());
            recopilacion.setPublica(true);

            recopilacionFacade.create(recopilacion);

            if (recopilacion.getId() != null) {

                Recopilacion viejaLista = recopilacionFacade.find(lista);

                Recocancion recopilacionCancion;
                for (Recocancion viejaRelacion : viejaLista.getRecocancionList()) {
                    recopilacionCancion = new Recocancion();
                    recopilacionCancion.setCancion(viejaRelacion.getCancion());
                    recopilacionCancion.setRecopilacion(recopilacion);

                    recopilacion.getRecocancionList().add(recopilacionCancion);

//                    recoCancionFacade.create(recopilacionCancion);
                }
                recopilacionFacade.edit(recopilacion);
                url = "recopilacion?id=" + recopilacion.getId();
            } else {
                url = "recopilaciones";
            }
        }

        response.sendRedirect(url);

    }

    private String getParameter(
            HttpServletRequest request, String parametername) {
        String ret = "";
        if (request.getParameter(parametername) != null) {
            ret = request.getParameter(parametername);
        }
        return ret;
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
