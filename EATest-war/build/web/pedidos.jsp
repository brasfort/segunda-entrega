<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>

<c:if test="${empty pedidos}">
    <h1>No hay pedidos</h1>
</c:if>
<c:if test="${!empty pedidos}">
<ul>
<c:forEach var="pedido" items="${pedidos}">
    <fmt:formatDate pattern="dd-MM-yyyy HH:mm" var="fecha" value="${pedido.fechapedido}" />
    <li style="border-bottom: 1px dotted gray;margin-bottom: 2em;">
        <p>
            <strong>Fecha de pedido: </strong> ${fecha}<br />
            <strong>Total: </strong> ${pedido.total}
        </p>
        
        <p>
        <c:if test="${!empty pedido.pedidocancionList}">
        <c:forEach var="item" items="${pedido.pedidocancionList}">
            <strong>Canción: </strong> ${item.cancion.nombre} (${item.cancion.precio})
            <br />
        </c:forEach>
        </c:if>
        <c:if test="${!empty pedido.pedidodiscoList}">
        <c:forEach var="item" items="${pedido.pedidodiscoList}">
            <strong>Vinilo: </strong> ${item.discop.disco.nombre} (${item.discop.precio})
            
            <a href="elliminarpedido?id=${item.id}">cancelar</a>
            <a href="elliminarpedido?id=${item.id}">acusar de recibido</a>
            <br />
        </c:forEach>
        </c:if>
        </p>
        
        <p><a href="elliminarpedido?id=${pedido.id}">cancelar pedido</a></p>
    </li>
</c:forEach>
</ul>
</c:if>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>