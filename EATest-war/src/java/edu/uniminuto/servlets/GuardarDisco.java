/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servlets;

import edu.uniminuto.entidades.Disco;
import edu.uniminuto.entidades.Discopropietario;
import edu.uniminuto.entidades.Persona;
import edu.uniminuto.sesion.DiscoFacade;
import edu.uniminuto.sesion.DiscopropietarioFacade;
import edu.uniminuto.sesion.GeneroFacade;
import edu.uniminuto.sesion.InterpreteFacade;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Jhon Gómez
 */
@WebServlet(name = "GuardarDisco", urlPatterns = {"/guardardisco"})
public class GuardarDisco extends HttpServlet {
    
    @EJB
    private DiscoFacade discoFacade;
    @EJB
    private GeneroFacade generoFacade;
    @EJB
    private DiscopropietarioFacade dpFacade;
    @EJB
    private InterpreteFacade interpreteFacade;
    
    
    public static final String RUTA = "C:\\Users\\Jhon Gómez\\Documents\\NetBeansProjects\\EATest\\EATest-war\\web\\images\\";

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

        String nombre = "";
        long precio = 0;
        int anhio = 0;
        short genero = 1;
        int interprete = 1
                ;
        
        
        
//        String nombre = getParameter(request, "nombre");
//        long precio = Long.valueOf(getParameter(request, "precio"));
//        int anhio = Integer.valueOf(getParameter(request, "anhio"));
//
//        int genero = Integer.valueOf(getParameter(request, "genero"));
//        int interprete = Integer.valueOf(getParameter(request, "interprete"));
        
            String url = "";
        try {

            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            String imagen = "images/";

            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 4, new File("c;//tmp"));

            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();

                if (item.isFormField()) {
                    if (item.getFieldName().equals("nombre")) {
                        nombre =item.getString();
                    } else if (item.getFieldName().equals("anhio")) {
                        anhio =Integer.valueOf(item.getString());
                    } else if (item.getFieldName().equals("genero")) {
                        genero =Short.valueOf(item.getString());
                    } else if (item.getFieldName().equals("interprete")) {
                        interprete =Integer.valueOf(item.getString());
                    } else if (item.getFieldName().equals("precio")) {
                        precio =Long.valueOf(item.getString());
                    } 
                    
                } else {
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    String contentType = item.getContentType();
                    boolean isInMemory = item.isInMemory();
                    long sizeInBytes = item.getSize();

                    
//                    InputStream uploadedStream = item.getInputStream();
//                    uploadedStream.close();
//                    InputStream uploadedStream = item.getInputStream();
//                    uploadedStream.close();
                    imagen = imagen + fileName;
                    File uploadedFile = new File(RUTA+fileName);
                    
                    item.write(uploadedFile);
//                    } else {
//                        
//                    }
                }
            }
            
            java.util.Calendar cl = java.util.Calendar.getInstance();
            cl.set(anhio, 0, 0, 0, 0, 0);
            
            Disco disco = new Disco();
            disco.setGenero(generoFacade.find(genero));
            disco.setInterprete(interpreteFacade.find(interprete));
            disco.setNombre(nombre);
            disco.setImagen(imagen);
            disco.setAnhio(cl.getTime());
            
            discoFacade.create(disco);
            
            if (disco.getId() != null) {
            
                Discopropietario dp = new Discopropietario();
                dp.setDisco(disco);
                dp.setPropietario((Persona)request.getSession().getAttribute("usuario"));
                dp.setPrecio(precio);
                dp.setVendido(false);

                dpFacade.create(dp);
                
                url = "disco?id=" + disco.getId();
                
            } else {
                url = "fdisco?nombre=" + nombre +
                "&precio=" + precio +
                "&anhio=" + anhio +
                "&genero=" + genero +
                "&interprete=" + interprete;
            }

        } catch (FileUploadException ex) {
            Logger.getLogger(GuardarDisco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GuardarDisco.class.getName()).log(Level.SEVERE, null, ex);
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
