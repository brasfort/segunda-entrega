<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<c:if test="${not empty mensaje}">
    <p class="error">${mensaje}</p>
</c:if>

<h1>Interpretes</h1>
<ul class="three" id="interpretes">
    <c:forEach items="${interpretes}" var="interprete">
        <li>
            <p>
                ${interprete.nombre}<br />
                <a href="interprete?id=${interprete.id}">ver detalles</a>
                <c:if test="${!esInvitado}">
                    | 
                    <a href="finterprete?accion=editar&id=${interprete.id}">actualizar</a> |
                    <a href="interprete?accion=borrar&id=${interprete.id}">borrar</a>
                </c:if>
            </p>
        </li>
    </c:forEach>
</ul>
<div id="loader"></div>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>