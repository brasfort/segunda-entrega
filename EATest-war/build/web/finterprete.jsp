<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>



            <c:if test="${not empty $error}">
                <p class="error"> ${error}</p>
            </c:if>

            <form id="form" action="procesarinterprete" method="post">
                <fieldset>
                    
                    <legend>Datos de Interprete</legend>

                    <label title="" for="nombre">Nombre: </label>
                    <input title="" type="text" id="nombre" name="nombre" value="${interprete.nombre}" class="required alphanumeric" maxlength="100" />
                    <br />
                    
                    <div class="center">
                        <input name="accion" type="hidden" value="${accion}" />
                        <input id="id" name="id" type="hidden" value="${interprete.id}" />
                        <input type="submit" value="${accion}" />
                    </div>
                </fieldset>
            </form>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>
