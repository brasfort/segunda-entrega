/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.carro.Elemento;
import edu.uniminuto.carro.ListaElementos;
import edu.uniminuto.entidades.Cancion;
import edu.uniminuto.entidades.Discocancion;
import edu.uniminuto.entidades.Discopropietario;
import edu.uniminuto.entidades.Persona;
import edu.uniminuto.sesion.CancionFacade;
import edu.uniminuto.utils.SongStockUtils;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ListadodeCanciones", urlPatterns = {"/canciones"})
public class ListadodeCanciones extends HttpServlet {

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

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/canciones.jsp");

        request.setAttribute("canciones", cancionFacade.getCanciones());
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
        if (request.getAttribute("isget") != null) {
            doGet(request, response);
            return;
        }
        int ultimoId = Integer.valueOf(SongStockUtils.getParameter(request, "ultimoId"));

        List<Cancion> canciones = cancionFacade.getCanciones(ultimoId);

        response.setContentType("text/html;charset=UTF-8");
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

        List<Elemento> elementos
                = (request.getSession().getAttribute("carro") != null)
                        ? ((ListaElementos) request.getSession().getAttribute("carro")).getElementos()
                        : new ArrayList<Elemento>();

        long uid = (request.getSession().getAttribute("usuario") != null)
                ? ((Persona) request.getSession().getAttribute("usuario")).getId()
                : -1;

        boolean existe = false;
        boolean disponible = false;
        StringBuffer discos;

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            for (Cancion cancion : canciones) {
                out.println("<li>");

                out.println("<p>");
                out.println(cancion.getNombre() + "<br />");
                out.println(cancion.getAnhio() + "<br />");

                out.println("Duración: " + sdf.format(cancion.getDuracion()) + "<br />");
                out.println("Calidad: " + cancion.getCalidad() + "<br />");
                out.println("Precio: " + cancion.getPrecio() + "<br />");
                out.println(cancion.getInterprete().getNombre() + "<br />");
//                out.println("<a href=\"fcancion?accion=editar&id="
//                        + cancion.getId() + "\">editar</a> | ");
//                out.println("<a href=\"cancion?accion=borrar&id="
//                        + cancion.getId() + "\">eliminar</a> | ");
                out.println("<a href=\"cancion?id="
                        + cancion.getId() + "\">detalle</a>");
                out.println("</p>");

                existe = false;
                for (Elemento elemento : elementos) {
                    if (elemento.getCancionId() == cancion.getId()) {
                        existe = true;
                        break;
                    }
                }

                if (existe) {
                    out.println("Ya en el carrito de compra");
                } else {
                    out.println("<form action=\"agregarcancion\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"nombre\" value=\"" + cancion.getNombre() + "\" />");
                    out.println("<input type=\"hidden\" name=\"id\" value=\"" + cancion.getId() + "\" />");
                    out.println("<input type=\"hidden\" name=\"precio\" value=\"" + cancion.getPrecio() + "\" />");
                    out.println("<input type=\"submit\" value=\"agregar al carrito\" class=\"link\" />");
                    out.println("</form>");
                }

                discos = new StringBuffer("");

                if (!cancion.getDiscocancionList().isEmpty()) {
                    for (Discocancion dc : cancion.getDiscocancionList()) {
                        for (Discopropietario dp : dc.getDisco().getDiscopropietarioList()) {
                            if (uid != dp.getId()) {

                                if (!dp.getVendido()) {

                                    existe = false;
                                    for (Elemento elemento : elementos) {
                                        if (elemento.getDiscoId() == dp.getDisco().getId()) {
                                            existe = true;
                                            break;
                                        }
                                    }
                                    if (existe) {
                                        discos.append("<p>");
                                        discos.append(dp.getDisco().getNombre());
                                        discos.append("<strong>Ya en el carro de compra</strong></p>");
                                    } else {

                                        discos.append("<form class=\"inline\" action=\"agregardisco\" method=\"post\">");

                                        discos.append("<input type=\"hidden\" name=\"nombre\" value=\"");
                                        discos.append(dp.getDisco().getNombre());
                                        discos.append("\" />");

                                        discos.append("<input type=\"hidden\" name=\"vista\" value=\"canciones\" />");

                                        discos.append("<input type=\"hidden\" name=\"id\" value=\"");
                                        discos.append(dp.getId());
                                        discos.append("\" />");

                                        discos.append("<input type=\"hidden\" name=\"precio\" value=\"");
                                        discos.append(dp.getPrecio());
                                        discos.append("\" />");

                                        discos.append("<p>");
                                        discos.append(dp.getDisco().getNombre());
                                        discos.append("<input type=\"submit\" value=\"agregar vinilo \" class=\"link\" /></p>");

                                        discos.append("</form>");
                                    }
                                }
                            }
                        }
                    }
                }

                disponible = discos.length() > 0;

                if (disponible) {
                    out.println("<p>Presente en los siguientes discos: </p>");
                    out.println(discos.toString());
                }

                out.println("</li>");
            }
        }
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
