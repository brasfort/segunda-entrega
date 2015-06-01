/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Compra;
import edu.uniminuto.entidades.Compracancion;
import edu.uniminuto.entidades.Compradisco;
import edu.uniminuto.entidades.Pedido;
import edu.uniminuto.entidades.Pedidocancion;
import edu.uniminuto.entidades.Pedidodisco;
import edu.uniminuto.entidades.Persona;
import edu.uniminuto.sesion.CompraFacade;
import edu.uniminuto.sesion.CompracancionFacade;
import edu.uniminuto.sesion.CompradiscoFacade;
import edu.uniminuto.sesion.DiscopropietarioFacade;
import edu.uniminuto.sesion.PedidoFacade;
import edu.uniminuto.sesion.PedidocancionFacade;
import edu.uniminuto.sesion.PedidodiscoFacade;
import edu.uniminuto.utils.EnviarCorreo;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "SolicitudesVinilo", urlPatterns = {"/solicitud"})
public class SolicitudesVinilo extends HttpServlet {

    @EJB
    private PedidoFacade pedidoFacade;
    @EJB
    private CompraFacade compraFacade;
    @EJB
    private CompracancionFacade compraCancionFacade;
    @EJB
    private CompradiscoFacade compraDiscoFacade;
    @EJB
    private PedidodiscoFacade pedidoDiscoFacade;
    @EJB
    private PedidocancionFacade pedidoCancionFacade;
    @EJB
    private DiscopropietarioFacade discoPropietarioFacade;

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

        String url = "entrar.jsp";
        if (request.getSession().getAttribute("usuario") != null) {

            java.text.SimpleDateFormat sdt
                    = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String accion = getParameter(request, "accion");
            int id = Integer.valueOf(getParameter(request, "id"));

            
            Pedidodisco pedidoDisco = pedidoDiscoFacade.find(id);

            if (accion.equals("enviar")) {

                try {
                    pedidoDisco.setFechaenvio(sdt.parse(getParameter(request, "fecha")));
                } catch (Exception ex) {
                    pedidoDisco.setFechaenvio(new java.util.Date());
                }
                pedidoDisco.setEnviado(true);
            } else if (accion.equals("rechazar")) {
                
                pedidoDisco.setRechazado(true);
                pedidoDisco.setComentario(getParameter(request, "comentario"));
            }

            pedidoDiscoFacade.edit(pedidoDisco);

            EnviarCorreo ec = new EnviarCorreo();

            if (accion.equals("enviar")) {
                ec.enviarMensaje(pedidoDisco.getPedido().getComprador().getCorreo(), "El disco ha sido confirmado",
                        pedidoDisco.getDiscop().getPropietario().getNombres() + " ha confirmado el envío del disco " + pedidoDisco.getDiscop().getDisco().getNombre() + ","
                        + "estima que lo enviará en " + sdt.format(pedidoDisco.getFechaenvio()));
            } else {
                ec.enviarMensaje(pedidoDisco.getPedido().getComprador().getCorreo(), "El vendedor del disco no quiso venderlo",
                        pedidoDisco.getDiscop().getPropietario().getNombres() + " ha confirmado el rechazo de venta del disco " + pedidoDisco.getDiscop().getDisco().getNombre() + ".");
            }

            int pedidoId = pedidoDisco.getPedido().getId();
            
            
            Pedido pedido = pedidoFacade.find(pedidoId);
            if (accion.equals("enviar")) {
                
                List<Pedidodisco> pedidos = pedidoDiscoFacade.getSolicitudesporPropiedad(
                        pedidoDisco.getDiscop().getId());

                for (Pedidodisco pedidodisco : pedidos) {

                    
                    
                    pedidodisco.setRechazado(true);
                    pedidodisco.setComentario("Ya se vendió.");

                    pedidoDiscoFacade.edit(pedidodisco);

                    boolean hacerCompra = true;

                    for (Pedidodisco pd2 : pedidodisco.getPedido().getPedidodiscoList()) {
                        if (pd2.getEnviado() == false
                                && pd2.getRechazado() == false) {
                            hacerCompra = false;
                            break;
                        }
                    }

                    if (hacerCompra) {

                        hacerCompra(pedidodisco.getPedido());
                    }

                }
                pedidoDisco.getDiscop().setVendido(true);

                discoPropietarioFacade.edit(pedidoDisco.getDiscop());

            }


            boolean hacerCompra = true;
            for (Pedidodisco pedidodisco : pedido.getPedidodiscoList()) {
                if (pedidodisco.getEnviado() == false
                        && pedidodisco.getRechazado() == false) {
                    hacerCompra = false;
                    break;
                }
            }

            if (hacerCompra) {
                hacerCompra(pedido);
            }
            url = "solicitudes";
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
    }


    private Compra hacerCompra(Pedido pedido) {
        Compra compra = compraFacade.hacerCompra(pedido);

        if (compra.getId() != null) {


            Compracancion compraCancion;

            for (Pedidocancion pedidoCancion : pedido.getPedidocancionList()) {

                compraCancion = new Compracancion();
                compraCancion.setCancion(pedidoCancion.getCancion());
                compraCancion.setCompra(compra);
                compraCancion.setPrecio(pedidoCancion.getCancion().getPrecio());

                compra.getCompracancionList().add(compraCancion);

                pedidoCancionFacade.remove(pedidoCancion);
            }

            Compradisco compraDisco;
            for (Pedidodisco pd : pedido.getPedidodiscoList()) {
                if (pd.getEnviado() == true && pd.getRechazado() == false) {

                    compraDisco = new Compradisco();
                    compraDisco.setCompra(compra);
                    compraDisco.setDisco(pd.getDiscop().getDisco());
                    compraDisco.setVendedor(pd.getDiscop().getPropietario());
                    compraDisco.setPrecio(pd.getDiscop().getPrecio());
                    compraDisco.setCalificacion(5);

                    compra.getCompradiscoList().add(compraDisco);
                    pedidoDiscoFacade.remove(pd);
                }

            }

            //pedidoFacade.remove(pedido);

            compraFacade.edit(compra);
            pedido.setDescartado(true);
            pedidoFacade.edit(pedido);
        }
        return compra;
    }
    // </editor-fold>

}
