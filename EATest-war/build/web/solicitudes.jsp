<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
<%
    java.util.Date date = new java.util.Date();
    
    request.setAttribute("hoy", date);
%>
<c:if test="${!empty solicitudes}">
    <ul>
        <c:forEach var="solicitud" items="${solicitudes}">
            <fmt:formatDate pattern="yyyy-MM-dd HH:mm" var="fecha" value="${solicitud.pedido.fechapedido}" />
            <fmt:formatDate pattern="yyyy-MM-dd" var="def" value="${hoy}" />
                            
            <li style="border-bottom: 1px dotted gray;margin-bottom: 2em;">
                <p>
                    <strong>Vinilo: </strong> ${solicitud.discop.disco.nombre} (${solicitud.discop.precio})
                    <strong>Fecha de pedido: </strong> ${fecha}<br />
                    <strong>Pedido de: </strong> ${solicitud.pedido.comprador.nombres} ${solicitud.pedido.comprador.apellidos}<br />
                    <strong>Correo: </strong> ${solicitud.pedido.comprador.correo}<br />
                    <strong>Número de compras: </strong> ${fn:length(solicitud.pedido.comprador.compraList)}<br />
                </p>

                <ul class="two">
                    <li>
                        <form action="solicitud" method="post" class="inline">
                            <h2>Acepto la solicitud</h2>
                            <input type="hidden" name="accion" value="enviar" />
                            <label>Fecha en que lo enviará: </label>
                            <input type="hidden" name="id" value="${solicitud.id}" />
                            <input type="text" name="fecha" value="${def}"/> aaaa-mm-dd
                            <br />
                            <input type="submit" class="link" value="lo enviaré" />
                        </form>
                    </li>
                    <li>
                        <form action="solicitud" method="post">
                            <h2>No estoy de acuerdo, rechazo la solucitud</h2>
                            <textarea style="width: 90%;" name="comentario">Indique por favor, cuál es el motivo del rechazo</textarea>
                            <input type="hidden" name="id" value="${solicitud.id}" />
                            <input type="hidden" name="accion" value="rechazar" />
                            <br />
                            <input type="submit" class="link" value="rechazar" />
                        </form>

                    </li>

                </ul>
            </li>
        </c:forEach>
    </ul>
</c:if>
<c:if test="${empty solicitudes}">
    <h1>No hay solicitudes</h1>
</c:if>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>