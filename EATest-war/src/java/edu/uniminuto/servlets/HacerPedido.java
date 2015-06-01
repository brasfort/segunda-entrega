/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.carro.Elemento;
import edu.uniminuto.carro.ListaElementos;
import edu.uniminuto.entidades.Cancion;
import edu.uniminuto.entidades.Compra;
import edu.uniminuto.entidades.Compracancion;
import edu.uniminuto.entidades.Discopropietario;
import edu.uniminuto.entidades.Pedido;
import edu.uniminuto.entidades.Pedidocancion;
import edu.uniminuto.entidades.Pedidodisco;
import edu.uniminuto.entidades.Persona;
import edu.uniminuto.sesion.CancionFacade;
import edu.uniminuto.sesion.CompraFacade;
import edu.uniminuto.sesion.CompracancionFacade;
import edu.uniminuto.sesion.DiscopropietarioFacade;
import edu.uniminuto.sesion.PedidoFacade;
import edu.uniminuto.sesion.PedidocancionFacade;
import edu.uniminuto.sesion.PedidodiscoFacade;
import edu.uniminuto.utils.EnviarCorreo;
import java.io.IOException;
import java.math.BigInteger;
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
@WebServlet(name = "HacerPedido", urlPatterns = {"/hacerpedido"})
public class HacerPedido extends HttpServlet {
    @EJB
    private CompraFacade compraFacade;
    @EJB
    private CancionFacade cancionFacade;
    @EJB
    private CompracancionFacade compraCancionFacade;
    @EJB
    private PedidoFacade pedidoFacade;
    @EJB
    private PedidocancionFacade pedidoCancionFacade;
    @EJB
    private DiscopropietarioFacade discoPropietarioFacade;
    @EJB
    private PedidodiscoFacade pedidoDiscoFacade;
    

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

        String url = "entrar.jsp?error=Para poder hacer el pedido debe haber iniciado sesion";
        if (request.getSession().getAttribute("usuario") != null) {

            Persona usuario = (Persona) request.getSession().getAttribute("usuario");

            ListaElementos le = new ListaElementos();
            if (request.getSession().getAttribute("carro") != null) {
                le = (ListaElementos) request.getSession().getAttribute("carro");
            }

            if (!le.isEmpty()) {

                if (!le.hayDiscos()) {
                    
                    Cancion cancion;
                    Compra compra = new Compra();
                    compra.setComprador(usuario);
                    compra.setFecha(new java.util.Date());
                    compra.setTotal(le.getTotal());
                    
                    compraFacade.create(compra);
                    
                    Compracancion compraCancion;

                    for (Elemento item : le.getElementos()) {
                        cancion = cancionFacade.find(item.getCancionId());
                        
                        compraCancion = new Compracancion();
                        compraCancion.setCancion(cancion);
                        compraCancion.setCompra(compra);
                        compraCancion.setPrecio(cancion.getPrecio());
                        
                        compraCancionFacade.create(compraCancion);
                    }

                } else {

                    Pedido pedido = new Pedido();
                    pedido.setComprador(usuario);
                    pedido.setFechapedido(new java.util.Date());
                    pedido.setTotal(BigInteger.valueOf(le.getTotal()));
                    pedido.setDescartado(false);
                    
                    pedidoFacade.create(pedido);
                    

                    Pedidocancion pedidoCancion;
                    Pedidodisco pedidoDisco;
                    Cancion cancion;
                    Discopropietario propietarioDisco;


                    for (Elemento elemento : le.getElementos()) {
                        if (elemento.getCancionId() != null) {
                            cancion = cancionFacade.find(elemento.getCancionId());

                            pedidoCancion = new Pedidocancion();
                            pedidoCancion.setCancion(cancion);
                            pedidoCancion.setPedido(pedido);
                            
                            
//                            pedidoCancionFacade.create(pedidoCancion);
                            pedido.getPedidocancionList().add(pedidoCancion);
                            
                        } else if (elemento.getDiscoId() != null) {

                            propietarioDisco = discoPropietarioFacade.find(elemento.getDiscoId());

                            pedidoDisco = new Pedidodisco();
                            pedidoDisco.setDiscop(propietarioDisco);
                            pedidoDisco.setPedido(pedido);
                            pedidoDisco.setEnviado(false);
                            pedidoDisco.setRechazado(false);
                            
                            
                            pedido.getPedidodiscoList().add(pedidoDisco);
//                            pedidoDiscoFacade.create(pedidoDisco);
                            
                            EnviarCorreo ec = new EnviarCorreo();

                            ec.enviarMensaje(propietarioDisco.getPropietario().getCorreo(), "SongStock! se ha hecho un pedido", 
                                    "Se ha solicitado el disco " + propietarioDisco.getDisco().getNombre() + "<br />" +
                                            "Lo solicitó " + usuario.getNombres());
                            
                        }
                    }
                    pedidoFacade.edit(pedido);
                }
                
                request.getSession().setAttribute("carro", null);
                url = "canciones";
            } else {
                url = "carro.jsp";
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
    }// </editor-fold>

}
