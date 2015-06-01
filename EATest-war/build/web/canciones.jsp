<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
    <h1>Listado de Canciones</h1>

<c:if test="${!esInvitado}">
    <ul>
        <li><a href="fcancion">Agregar</a></li>
    </ul>
</c:if>

<c:if test="${not empty mensaje}">
    <p class="info">${mensaje}</p>
</c:if>

<ul class="lists">
    <li><a href="#" id="clist" title="Cambiar a lista"></a></li>
    <li><a href="#" id="ctlist" title="Cambiar a dos columnas"></a></li>
    <li><a href="#" id="ctrlist" title="Cambiar a tres columnas"></a></li>
</ul>
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
    window.doQuery = true;
    $(document).on("ready", function () {
        $('#clist').bind('click', function (event) {
            event.preventDefault();
            $('#cancions').removeClass('three');
            $('#cancions').removeClass('two');
        });
        $('#ctlist').bind('click', function (event) {
            event.preventDefault();
            $('#cancions').removeClass('three');
            $('#cancions').addClass('two');
        });
        $('#ctrlist').bind('click', function (event) {
            event.preventDefault();
            $('#cancions').removeClass('two');
            $('#cancions').addClass('three');
        });
        if ($('ul#cancions li').size() < 20) {
            return;
        }
        $(window).scroll(function () {
            if ($(window).scrollTop() == $(document).height() - $(window).height()) {
                loadMoreCancions();
            }
        });
    });

    function loadMoreCancions() {
        if (window.doQuery == false) {
            $('div#loader').html('No se encontraron más registros');
            return;
        }

        var lastId = $('#cancions li').size();
        $('div#loader').html('<img class="center" src="http://localhost/songstock/images/load.gif" alt="Cargando...">');
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/canciones',
            data: 'ultimoId=' + lastId + '&asList=true',
            success: function (msg) {
                msg = msg.replace(/^\s+/g, '').replace(/\s+$/g, '');
                if (msg.length > 0) {
                    $("ul#cancions li:last").after(msg);
                    $('div#loader').empty();
                } else {
                    window.doQuery = false;
                    $('div#loader').html('No se encontraron más registros');
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest);
                alert("some error" + XMLHttpRequest + "; " + textStatus + "; " + errorThrown);
                window.doQuery = false;
                $('div#loader').empty();
            }
        });
    }
</script>

<ul class="three" id="cancions">
    <c:forEach items="${canciones}" var="cancion">

        <fmt:formatDate pattern="mm:ss" var="duracion" value="${cancion.duracion}" />
        <li>
            <p>
                ${cancion.nombre}<br />
                ${cancion.anhio}<br />
                Duración: ${duracion}<br />
                Calidad: ${cancion.calidad} kbps<br />
                Precio: ${cancion.precio}<br />
                ${cancion.interprete.nombre}<br />
<!--                        <a href="fcancion?accion=editar&id=${cancion.id}">editar</a> | 
                <a href="cancion?accion=borrar&id=${cancion.id}">eliminar</a> |  -->
                <a href="cancion?id=${cancion.id}">detalles</a> 
            </p>

            <c:set var="existe" scope="page" value="0" />

            <c:if test="${not empty listado}">
                <c:forEach items="${listado}" var="elemento">
                    <c:if test="${existe == '1' or (not empty elemento.cancionId and elemento.cancionId eq cancion.id)}">
                        <c:set var="existe" scope="page" value="1" />
                    </c:if>
                </c:forEach>    
            </c:if>

            <c:if test="${existe == '0'}">
                <form action="agregarcancion" method="post" class="inline">
                    <input type="hidden" name="nombre" value="${cancion.nombre}" />
                    <input type="hidden" name="id" value="${cancion.id}" />
                    <input type="hidden" name="precio" value="${cancion.precio}" />
                    <input type="submit" value="agregar al carrito" class="link" />
                </form>
            </c:if>

            <c:if test="${existe == '1'}">
                Ya en el carrito de compra
            </c:if>


            <c:set var="disponible" scope="page" value="0" />
            <c:if test="${not empty cancion.discocancionList}">
                <c:forEach items="${cancion.discocancionList}" var="dc">

                    <c:forEach items="${dc.disco.discopropietarioList}" var="dp">

                        <c:if test="${disponible == '1' or (empty usuario or usuario.id != dp.propietario.id)}">
                            <c:set var="disponible" scope="page" value="1" />
                        </c:if>  
                    </c:forEach>

                </c:forEach>   
            </c:if>

            <c:if test="${disponible == '1'}">
                <p>Presente en los siguientes discos: </p>
                <c:forEach items="${cancion.discocancionList}" var="dc">
                    <c:forEach items="${dc.disco.discopropietarioList}" var="dp">

                        <c:set var="existe" scope="page" value="0" />

                        <c:if test="${not empty listado}">
                            <c:forEach items="${listado}" var="elemento">
                                <c:if test="${existe == '1' or (not empty elemento.discoId and elemento.discoId == dp.id)}">
                                    <c:set var="existe" scope="page" value="1" />
                                </c:if>
                            </c:forEach>    
                        </c:if>
                        
                        <c:if test="${dp.vendido eq true}">
                            <c:set var="existe" scope="page" value="3" />
                        </c:if>


                        <c:if test="${existe == '1'}">
                            <p>
                                ${dp.disco.nombre} <strong>Ya en el carro de compra</strong>
                            </p>
                        </c:if>
                        <c:if test="${existe == '0'}">
                            <form class="inline" action="agregardisco" method="post">
                                <input type="hidden" name="nombre" value="${dp.disco.nombre}" />
                                <input type="hidden" name="id" value="${dp.id}" />
                                <input type="hidden" name="precio" value="${dp.precio}" />
                                <input type="hidden" name="vista" value="canciones" />

                                <p>
                                    ${dp.disco.nombre}
                                    <input type="submit" value="agregar vinilo" class="link" />
                                </p>

                            </form>

                        </c:if>


                    </c:forEach>   
                </c:forEach>

            </c:if>
        </li>
    </c:forEach>
</ul>
<div id="loader"></div>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>