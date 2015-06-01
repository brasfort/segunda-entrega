<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
            </div><!-- end main -->
            <div id="sidebar">
                <ol>
                    <c:if test="${esInvitado}">
                    <li><a href="entrar.jsp">Entrar</a></li>
                    <li><a href="registro">Registrarme</a></li>
                    <li><a href="canciones">Canciones</a></li>
                    <li><a href="index">Discos</a></li>
                    <li><a href="interpretes">Interpretes</a></li>
                    </c:if>
                    <c:if test="${!esInvitado}">
                    <li><a href="canciones">Canciones</a></li>
                    <li><a href="index">Discos</a></li>
                    <li><a href="interpretes">Interpretes</a></li>
                    <li><a href="recopilaciones">Recopilaciones</a></li>
                    <li><a href="recopilaciones?propias=si">Mis recopilaciones</a></li>
                    <c:if test="${rol eq 1}">
                        <li><a href="pedidos">Mis pedidos</a></li>
                        <li><a href="admcancion">Buscar canciones</a></li>
                        <li><a href="compras">Mis compras</a></li>
                    </c:if>
                    <c:if test="${rol eq 2}">
                        <li><a href="admcancion">Buscar canciones</a></li>
                        <li><a href="fdisco">Agregar Disco</a></li>
                        <li><a href="fcancion">Agregar Canción</a></li>
                    
                        <li><a href="solicitudes">Solicitudes (vinilos)</a></li>
                        <li><a href="fdisco">Quiero vender un vinilo</a></li>
                    
                    </c:if>
                    <li><a href="registro">Yo</a></li>
                    <li><a href="salir">Salir</a></li>
                    </c:if>
                </ol>
            </div><!-- end sidebar -->
        </div><!-- end content -->
        <div id="foot">
            <div class="band"> 
                <p>Todos los derechos reservados &copy; 2015</p>
            </div>
        </div><!-- end foot -->
    </div><!-- end page -->
</body>
</html>
