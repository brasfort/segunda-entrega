<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>

            <h1>Canción</h1>
            <ul>
                <c:if test="${!esInvitado}">
                    <li><a href="fcancion?accion=crear">agregar</a></li>
                </c:if>
                <li><a href="canciones">Listado</a></li>
            </ul>

            
            <fieldset>
                <legend>Detalle Cancion</legend>
                <fmt:formatDate pattern="mm:ss" var="duracion" value="${cancion.duracion}" />
                <p>
                    <strong>Nombre: </strong> ${cancion.nombre}<br />
                    <strong>Duración: </strong> ${duracion}<br />
                    <strong>Calidad: </strong> ${cancion.calidad} kbps<br />
                    <strong>Peso: </strong> ${cancion.peso} MB<br />
                    <strong>Precio: </strong> ${cancion.precio}<br />
                    <strong>Año: </strong> ${cancion.anhio}<br />
                    <strong>Intérprete:  </strong> <a href="interprete?id=${cancion.interprete.id}">${cancion.interprete.nombre}</a> <br />
                    <c:if test="${!esInvitado}">
                        <a href="fcancion?accion=editar&id=${cancion.id}">editar</a> | 
                        <a href="cancion?accion=borrar&id=${cancion.id}">eliminar</a>
                    </c:if>
                </p>
                <c:if test="${!empty recos}">
                <p>
                    Puede agregarla a las siguientes listas <br />
                    <c:forEach items="${recos}" var="reco">
                     ${reco.nombre} 
                    <a href="cancionreco?accion=agregar&cancion=${cancion.id}&recopilacion=${reco.id}">agregar</a> | 
                    <a href="recopilacion?id=${reco.id}">ver detalles</a><br />
                    </c:forEach>
                </p>    
                </c:if>
                
                <c:if test="${!empty rels}">
                <p>
                    Esta canción ya está presente en tus listas: <br />
                    <c:forEach items="${rels}" var="reco">
                     ${reco.nombre} 
                    <a href="cancionreco?vista=cancion&accion=borrarc&cancion=${cancion.id}&recopilacion=${reco.id}">borrar</a> | 
                    <a href="recopilacion?id=${reco.id}">ver detalles</a><br />
                    </c:forEach>
                </p>    
                </c:if>
            </fieldset>

            <c:if test="${(rol eq 2 or rol eq 1) and !empty discos}">
                <c:forEach items="${discos}" var="disco">

                    
                    <c:set var="relacionado" value="0" scope="page" />
                    
                    <c:if test="${!empty cancion.discocancionList}">    
                        <c:forEach items="${cancion.discocancionList}" var="dc">
                            <c:if test="${relacionado == '1' or (dc.disco.id eq disco.id)}">
                                <c:set var="relacionado" value="1" scope="page" />
                            </c:if>
                            <c:if test="${dc.disco.id eq disco.id}">
                                <c:set var="relacionado" value="1" scope="page" />
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${relacionado eq '1'}">
                        Ya agregado a <a href="disco?id=${disco.id}">${disco.nombre}</a> <br />
                    </c:if>
                    <c:if test="${relacionado eq '0'}">
                        <a href="cancion?accion=agregar&id=${cancion.id}&disco=${disco.id}">Agregar al vinilo ${disco.nombre}</a><br />
                    </c:if>
                </c:forEach>
            </c:if>
            <c:if test="${(not empty accion) && (accion == 'borrar')}">
            <fieldset>
                <legend>Borrar registro</legend>
                <form action="procesacancion" method="post">
                    <input type="hidden" id="id" name="id" value="${cancion.id}" />
                    <input type="hidden" id="accion" name="accion" value="borrar" />
                    <p>¿Realmente desea borrar éste registro? <em>Tenga en cuenta que si este registro está conectado con otros registro no podrá eliminiarlo hasta que elimine previamente los datos relacionados</em>.</p>
                    <div class="center">
                        <input type="submit" value="sí" />
                    </div>
                </form>
            </fieldset>
            </c:if>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>