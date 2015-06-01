<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>

<form action="admcancion">
    <label>Buscar canciones: </label>
    <input type="text" name="qu" value="${qu}"/><br />
    <input type="submit" value="buscar" />
</form>

<ul>
    <c:forEach items="${canciones}" var="cancion">
        <li>
            ${cancion.nombre} <a href="cancion?id=${cancion.id}">detalles</a>
            <p>

                <c:forEach items="${discos}" var="disco">

                    <c:set var="relacionado" value="false" />
                    
                    <c:if test="${!empty cancion.discocancions}">    
                        <c:forEach items="${cancion.discocancions}" var="dc">
                            
                            <c:if test="${dc.disco.id eq disco.id and !relacionado}">
                                <c:set var="relacionado" value="true" />
                                Ya agregado a <a href="disco?id=${disco.id}">${disco.nombre}</a> <br />
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${!relacionado}">
                        <a href="agregarcancion?accion=agregar&qu=${qu}&cancion=${cancion.id}&disco=${disco.id}">Agregar al vinilo ${disco.nombre}</a><br />
                    </c:if>
                </c:forEach>
            </p>
        </li>
    </c:forEach>
</ul>

<jsp:include page="/styles/templates/foot.jsp"></jsp:include>