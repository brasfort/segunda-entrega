<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
            <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
            <script type="text/javascript">
                 window.doQuery = true;
                 $(document).on("ready", function() {
//                    $('#clist').bind('click', function(event) {     
//                        event.preventDefault();
//                        $('#interpretes').removeClass('three');
//                        $('#interpretes').removeClass('two');
//                    });
//                    $('#ctlist').bind('click', function(event) {     
//                        event.preventDefault();
//                        $('#interpretes').removeClass('three');
//                        $('#interpretes').addClass('two');
//                    });
//                    $('#ctrlist').bind('click', function(event) {     
//                        event.preventDefault();
//                        $('#interpretes').removeClass('two');
//                        $('#interpretes').addClass('three');
//                    });
	                if ($('ul#recopilaciones li').size() < 20) {
	                	return;
	                }
                    $(window).scroll(function(){
                        if  ($(window).scrollTop() == $(document).height() - $(window).height()){
                           loadMoreInterpretes();
                        }
                    }); 
                });

                function loadMoreInterpretes() {
                    if (window.doQuery == false) {
                        $('div#loader').html('No se encontraron más registros');
                        return;
                    }

                    var lastId = $('#recopilaciones li').size();
                    $('div#loader').html('<img class="center" src="images/load.gif" alt="Cargando...">');
                    $.ajax({
                      type: 'GET',
                      url: '${pageContext.request.contextPath}/procesarinterprete',
                      data: 'ultimoId=' + lastId,
                      success: function(msg){
                        msg = msg.replace(/^\s+/g,'').replace(/\s+$/g,'');
                        if (msg.length > 0) {
                            $("ul#recopilaciones li:last").after(msg);
                            $('div#loader').empty();
                        } else {
                            window.doQuery = false;
                            $('div#loader').html('No se encontraron más registros');
                        }
                      },
                      error: function(XMLHttpRequest, textStatus, errorThrown) {
                         alert("some error" + XMLHttpRequest + "; " + textStatus + "; " + errorThrown);
                         window.doQuery = false;
                         $('div#loader').empty();
                      }
                    });
                }
            </script>


            <c:if test="${not empty mensaje}">
                <p class="error">${mensaje}</p>
            </c:if>
                
                
                <c:if test="${!esInvitado}">
    <ul>
        <li>
            <a href="frecopilacion">crear una nueva recopilacion</a>
        </li>
    
    </ul>
    
</c:if>  


            <h1>Recopilaciones</h1>
            
            
            
            <ul id="recopilaciones">
                <c:forEach items="${recos}" var="reco">
                    <li>
                        
                        <h2>${reco.nombre}</h2>
                    
                        <fmt:formatDate pattern="dd-MM-yyyy" var="fecha" value="${reco.fecha}" />
                        <p>
                            Creado por: ${reco.propietario.nombres} (${fecha})<br />
                        </p>
                        <form action="cancionreco" method="post">
                            <input type="hidden" name="recopilacion" value="${reco.id}" />
                            <label>Nombre de la nueva lista: </label>
                            <input type="text" name="nombre" />
                            <input type="submit" value="copiar esta lista" />
                        </form>
                        
                        
                        <p>
                            <c:forEach items="${reco.recocancionList}" var="rc"> 
                            ${rc.cancion.nombre} <a href="cancion?id=${rc.cancion.id}">ver</a><br />
                            </c:forEach>    
                        </p>
                        
                        <p>
                            <a href="recopilacion?id=${reco.id}">ver detalles</a>
                            <c:if test="${uid eq reco.propietario.id}"> | 
                            <a href="frecopilacion?accion=editar&id=${reco.id}">actualizar</a> |
                            <a href="recopilaciones?accion=borrar&id=${reco.id}">borrar</a>
                            </c:if>
                        </p>
                    </li>
                </c:forEach>
            </ul>
            <div id="loader"></div>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>