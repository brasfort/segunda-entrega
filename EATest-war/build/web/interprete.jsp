<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>

            <h1>Interprete</h1>
            <ul>
                <c:if test="${!esInvitado}">
                <li><a href="finterprete">Agregar</a></li>
                </c:if>
                <li><a href="interpretes">Listado</a></li>
            </ul>

            <fieldset>
                <legend>Detalle Interprete</legend>
                <p>
                    <strong>Nombre: </strong> ${interprete.nombre}<br />
                    
                    <c:if test="${!esInvitado}">
                    <a href="finterprete?accion=actualizar&id=${interprete.id}">actualizar</a> | 
                    <a href="interprete?accion=borrar&id=${interprete.id}">borrar</a>
                    </c:if>
                </p>
            </fieldset>

            <c:if test="${not empty interprete.discoList}">
            <fieldset>
                <legend>Listado de álbumes</legend>
                <ol>
                    <c:forEach var="disco" items="${interprete.discoList}">
                    <li>
                        <strong>Nombre: </strong> ${disco.nombre} 
                        (<a href="disco?&id=${disco.id}">ver</a>)<br />
                    </li>
                    </c:forEach>
                </ol>
            </fieldset>
            </c:if>
            
            <c:if test="${(not empty accion) && (accion == 'borrar')}">
            <fieldset>
                <legend>Borrar registro</legend>
                <form action="procesarinterprete" method="post">
                    <input type="hidden" id="id" name="id" value="${interprete.id}" />
                    <input type="hidden" id="accion" name="accion" value="borrar" />
                    <p>¿Realmente desea borrar éste registro? <em>Tenga en cuenta que si este registro está conectado con otros registro no podrá eliminiarlo hasta que elimine previamente los datos relacionados</em>.</p>
                    <div class="center">
                        <input type="submit" value="sí" />
                    </div>
                </form>
            </fieldset>
            </c:if>

<jsp:include page="/styles/templates/foot.jsp"></jsp:include>