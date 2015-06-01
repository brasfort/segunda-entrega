<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>

    <script type="text/javascript">
        function mostrar(elemento) {
            divs = elemento.parentNode.getElementsByTagName('div');
            div = divs[0];
            div.style.display = 'block';

            elemento.style.display = 'none';
        }

    </script>
<c:if test="${empty compras}">
    <h1>Aún no has realizado compras</h1>
</c:if>
<c:if test="${!empty compras}">
    <ol>
        <c:forEach items="${compras}" var="compra">
            <li>
                <p>
                    <fmt:formatDate pattern="yyyy-MM-dd" value="${compra.fecha}" var="fecha" />
                    <strong>Total: </strong>$ ${compra.total}<br />
                    <strong>Fecha: </strong>${fecha}<br />
                </p>
                <p>
                    <c:forEach items="${compra.compracancionList}" var="item">
                        ${item.cancion.nombre} <a href="cancion?id=${item.cancion.id}">detalle</a> (canción)<br />
                    </c:forEach>
                </p>
                <c:forEach items="${compra.compradiscoList}" var="item">
                    <p>
                        ${item.disco.nombre} <a href="disco?id=${item.disco.id}">detalle</a>  (vinilo) <br />
                        Calificación: ${item.calificacion} 
                        <div class="formulario" style="display: none;">
                            <form action="compras" method="post">
                                <label for="calificar">Calificar</label>
                                <input type="hidden" value="${item.id}" name="cdid" />
                                <select id="calificar" name="calificacion">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                                <input type="submit" value="volver a calificar" />
                            </form>
                        </div>
                        <button id="btn" onclick="mostrar(this)">volver a calificar</button>
                    </p>
                </c:forEach>
                <c:if test="${not empty compra.observaciones}">
                    <p><strong>Observaciones</strong>: ${compra.observaciones}</p>
                </c:if>
                </li>
            </c:forEach>
    </ol>
</c:if>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>