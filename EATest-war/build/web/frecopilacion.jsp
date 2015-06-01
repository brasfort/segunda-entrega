<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>

            <h1>Recopilacion</h1>
            <ul>
                <li><a href="frecopilacion">Agregar</a></li>
                <li><a href="recopilaciones">Listado</a></li>
                <c:if test="${not empty recopilacion.id}">
                <li><a href="recopilacion?id=${recopilacion.id}">ver detalles</a></li>
                </c:if>
            </ul>

            <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
            <script type="text/javascript" src="js/jquery.validate.min.js"></script>
            <script type="text/javascript" src="js/recopilacion.js"></script>
            <script type="text/javascript">
                $(document).on("ready", function() {
                    assignEvents();
                });
            </script>

            <form id="form" action="guardarrecopilacion" method="post">
                <fieldset>
                    <legend>Datos de Recopilacion</legend>

                    <label title="" for="Nombre">Nombre: </label>
                    <input title="" type="text" id="Nombre" name="nombre" value="${recopilacion.nombre}" class="required alphanumeric" maxlength="200" />
                    <br />

                    <label title="" for="Publica">Publica: </label>
                    
                    <input title="" type="checkbox" id="Publica" name="publica" <c:if test="${recopilacion.publica eq true}">checked="checked" </c:if> />
                    <br />
                    
                    <div class="center">
                        <input id="Id" name="id" type="hidden" value="${recopilacion.id}" />
                        <input name="accion" type="hidden" value="${(recopilacion.id == null) ? "crear" : "editar"}" />
                        <input type="submit" value="guardar" />
                    </div>
                </fieldset>
            </form>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>