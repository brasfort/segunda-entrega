
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/styles/templates/header.jsp"></jsp:include>
            <c:if test="${not empty mensaje}">
                <p class="error">${mensaje}</p>
            </c:if>
                
                
<c:if test="${!esInvitado}">
    <ul>
        <li>
            <a href="frecopilacion">crear una nueva recopilacion</a>
        </li>
    
    </ul>
    
</c:if>  
                
                

            <h1>Recopilacion</h1>
                        
                        <h2>${reco.nombre}</h2>

                        <fmt:formatDate pattern="yyyy-MM-dd" var="fecha" value="${reco.fecha}" />
                        <p>
                            Creado por: ${reco.propietario.nombres} (${fecha})<br />
                            
                            <span class="red">
                                <c:if test="${reco.publica eq true}">publica</c:if>
                                <c:if test="${reco.publica eq false}">privada</c:if>
                            </span>
                        </p>
                        <form action="cancionreco" method="post">
                            <input type="hidden" name="recopilacion" value="${reco.id}" />
                            <label>Nombre de la nueva lista: </label>
                            <input type="text" name="nombre" />
                            <input type="submit" value="copiar esta lista" />
                        </form>
                        
                        
                        <p>
                            <c:forEach items="${reco.recocancionList}" var="rc"> 
                            ${rc.cancion.nombre} <a href="cancion?id=${rc.cancion.id}">ver</a> 
                            <c:if test="${uid eq reco.propietario.id}"> | 
                            <a href="cancionreco?accion=borrarc&recopilacion=${reco.id}&id=${reco.id}&relacion=${rc.id}">borrar de la lista</a>
                            </c:if>
                            <br />
                            </c:forEach>
                        </p>
                        
                        <p>
                            <c:if test="${uid eq reco.propietario.id}">
                            <a href="frecopilacion?accion=editar&id=${reco.id}">actualizar</a> |
                            <a href="recopilacion?accion=borrar&id=${reco.id}">borrar</a>
                            </c:if>
                        </p>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>