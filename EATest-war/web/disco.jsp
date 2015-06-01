<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
                <h1>Disco</h1>
                <ul>
                    <c:if test="${!esInvitado}">
                        <li><a href="fdisco?accion=crear">agregar</a></li>
                    </c:if>
                    <li><a href="index">Listado</a></li>
                </ul>

                <fieldset>
                    <legend>Detalle Disco</legend>
                    
                    <ul class="three">
                        <li><img class="total" src="${disco.imagen}" /></li>
                        
                        <li style="width: 60%;">
                            <p>
                                <strong>Nombre: </strong> ${disco.nombre}<br />
                                <!--<strong>Precio: </strong> <br />-->
                                <fmt:formatDate pattern="yyyy" var="year" value="${disco.anhio}" />
                                <strong>Año: </strong> ${year}<br />
                                
                                
                                <strong title="">Genero: </strong> ${disco.genero.nombre}
                                <br />

                                <strong title="">Interprete </strong> ${disco.interprete.nombre} 
                                <a href="interprete?id=${disco.interprete.id}">ver</a>
                                <br />

                                <c:if test="${!esInvitado}">
                                    <a href="fdisco?accion=editar&id=${disco.id}">actualizar</a> | 
                                    <a href="disco?accion=borrar&id=${disco.id}">borrar</a>
                                </c:if>
                            </p>
                            
                            <c:if test="${!esInvitado}">
                                <form action="procesarvinilo" method="post">  
                                    <h2>Tengo este vinilo y quiero venderlo</h2>
                                    <label for="precio">Precio: </label>
                                    <input type="text" name="precio" id="precio" />
                                    <input type="hidden" name="disco" value="${disco.id}" />
                                    <input type="submit" value="ofrecer" />
                                </form>
                            </c:if>
                        </li>
                    </ul>
                            
                    <c:if test="${!empty disco.discocancionList}">
                    <h1> Listado de canciones</h1>
                    <ol>
                    <c:forEach var="item" items="${disco.discocancionList}">
                        <li>
                        ${item.cancion.nombre} (${item.cancion.interprete.nombre})
                        </li>
                    </c:forEach>
                    </ol>
                    </c:if>
                            
                    <c:if test="${!empty discopropietarioList}">
                    <h1> Listado de vendedores</h1>
                    <ol>
                    <c:forEach var="item" items="${discopropietarioList}">
                        <c:if test="${item.vendido eq false}">
                        <li>
                        <c:if test="${idsesion != item.propietario.id}">
                        ${item.propietario.nombres} ($ ${item.precio})
                        <form id="vender" action="agregardisco" method="post" class="inline">
                            <input type="hidden" name="nombre" value="${item.disco.nombre}" />
                            <input type="hidden" name="id" value="${item.id}" />
                            <input type="hidden" name="precio" value="${item.precio}" />
                            <input type="hidden" name="vista" value="disco?id=${item.disco.id}" />
                            <input type="submit" value="agregar al carrito" class="link" />
                        </form>
                        </c:if>
                        <c:if test="${idsesion == item.propietario.id}">
                        Tú ($ ${item.precio})
                        <form action="procesarvinilo" method="post" class="inline">
                            <input type="hidden" name="id" value="${item.id}" />
                            <input type="hidden" name="disco" value="${disco.id}" />
                            <input type="hidden" name="accion" value="borrar" />
                            <input type="submit" value="quitar" class="link" />
                        </form>
                        </c:if>
                        </li>
                        </c:if>
                    </c:forEach>
                    </ol>
                    </c:if>
                </fieldset>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>