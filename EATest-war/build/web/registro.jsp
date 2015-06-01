<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>

            <h1>Información personal</h1>


            <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
            <script type="text/javascript" src="js/jquery.validate.min.js"></script>
            <script type="text/javascript" src="js/persona.js"></script>
            <script type="text/javascript">
                $(document).on("ready", function() {
                    assignEvents();
                });
            </script>

            <form id="form" action="registro" method="post">
                <fieldset>
                    <legend>Información</legend>

                    <label title="" for="Nombres">Nombres: </label>
                    <input title="" type="text" id="Nombres" name="nombres" value="${persona.nombres}" class="required alphanumeric" maxlength="45" />
                    <br />

                    <label title="" for="Apellidos">Apellidos: </label>
                    <input title="" type="text" id="Apellidos" name="apellidos" value="${persona.apellidos}" class="required alphanumeric" maxlength="45" />
                    <br />

                    <label title="" for="Nacimiento">Nacimiento: </label>
                    <input title="" type="text" id="Nacimiento" name="nacimiento" value="${nacimiento}" class="required dateISO" />
                    <br />

                    <label title="" for="Nacimiento">formato <em>aaaa-mm-dd</em></label>
                    <br />
                    
                    <label title="" for="Correo">Correo: </label>
                    <input title="" type="text" id="Correo" name="correo" value="${persona.correo}" class="required alphanumeric" maxlength="100" />
                    <br />

                    <label title="" for="Clave">Clave: </label>
                    <input title="" type="password" id="Clave" name="clave" value="" class="required alphanumeric" maxlength="45" />
                    <br />

                    <c:if test="${!admin and esInvitado}">
                    <label title="" for="vendedor">Soy vendedor: </label>
                    <input title="" type="radio" id="vendedor" name="rol" value="vendedor" />
                    <br />
                    
                    <label title="" for="comprador">Soy comprador: </label>
                    <input title="" type="radio" id="comprador" name="rol" value="comprador" />
                    <br />
                    </c:if>
                    <c:if test="${admin}">
                    <label>Eres administador: </label>
                    <input title="" type="radio" id="comprador" name="rol" checked="checked" value="administrador" disabled="disabled" />
                    <br />
                    </c:if>
                    
                    <div class="center">
                        <input id="Id" name="Id" type="hidden" value="<?php echo $persona->getId(); ?>" />
                        <input type="submit" value="guardar" />
                    </div>
                </fieldset>
            </form>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>