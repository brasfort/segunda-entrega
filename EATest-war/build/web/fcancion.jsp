<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>

            <h1>Canción</h1>
            <ul>
                <li><a href="fcancion">Agregar</a></li>
            </ul>

            <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
            <script type="text/javascript" src="js/jquery.validate.min.js"></script>
            <script type="text/javascript" src="js/cancion.js"></script>
            <script type="text/javascript">
                $(document).on("ready", function() {
                    assignEvents();
                });
            </script>

            <form id="form" action="admcancion" method="post">
                <fieldset>
                    <legend>Datos de Cancion</legend>

                    <label title="" for="Nombre">Nombre: </label>
                    <input title="" type="text" id="Nombre" name="nombre" value="${cancion.nombre}" class="required alphanumeric" maxlength="250" />
                    <br />

                    <label title="" for="Anhio">Anhio: </label>
                    <input title="" type="text" id="Anhio" name="anhio" value="${cancion.anhio}" class="required alphanumeric" maxlength="4" />
                    <br />

                    <label title="" for="Duracion">Duracion: </label>
                    <input title="" type="text" id="Duracion" name="duracion" value="${cancion.duracion}" class="required" />
                    <br />
                    <label title="" for="Duracion">Formato HH:mm</label>
                    <br />
                    
                    
                    <label title="" for="Peso">Peso: </label>
                    <input title="" type="text" id="Peso" name="peso" value="${cancion.peso}" class="required alphanumeric" maxlength="7" /> 
                    <span>en <span title="MegaBytes">MB</span></span>
                    <br />

                    <label title="" for="Calidad">Calidad: </label>
                    <select name="calidad">
                        <option value="128">128</option>
                        <option value="192">192</option>
                        <option value="256">256</option>
                        <option value="320">320</option>
                    </select>
                    <span>en <span title="kiloBytes">kb</span>/<span title="segundos">s</span></span>
                    <br />

                    <label title="" for="Precio">Precio: </label>
                    <input title="" type="text" id="Precio" name="precio" value="${cancion.precio}" class="required digits" />
                    <br />

                    <label title="" for="interprete">Intérprete </label>
                    <select name="interprete">
                        <c:if test="${!empty cancion.interprete}">
                        <option value="${cancion.interprete.id}" selected>${cancion.interprete.nombre}</option>
                        </c:if>
                        <c:forEach items="${interpretes}" var="inter">
                        <c:if test="${cancion.interprete.id != inter.id}">
                        <option value="${inter.id}">${inter.nombre}</option>
                        </c:if>
                        </c:forEach>
                    </select>
                    <br />
                    
                    <div class="center">
                        <input id="Id" name="Id" type="hidden" value="${cancion.id}" />
                        <input type="submit" value="guardar" />
                    </div>
                </fieldset>
            </form>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>
