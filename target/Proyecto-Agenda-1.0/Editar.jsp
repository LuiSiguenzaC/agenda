<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/importes.html"%>
        <title>Agenda</title>
    </head>
    <body class="bg_body">
        <a href="index.jsp" class="btn btn-volver">Volver</a>
        <h1 class="cabecera-agenda">Editar nota</h1>
        <div class="contenedor2">
            
            <%
                int id = Integer.parseInt(request.getParameter("idnota"));
                int index = Integer.parseInt(request.getParameter("index"));
            %>
            
            <form action="EditarNota" method="POST" class="formulario u-padding-top">
                <legend>Editar esta Nota <span>todos los campos obligatorios</span></legend>
                <div class="seccion">
                    <div class="grupo">
                        <label for="descripcion" class="labels">Descripción:</label>
                        <textarea class="inputs" name="descripcion" id="descripcion" rows="4" cols="50" placeholder="nueva descripción"></textarea>
                    </div>
                    <div class="seccion start">
                        <div class="grupo wauto">
                            <label for="dia" class="labels">Día:</label>
                            <input type="number" class="inputs fecha" id="dia" name="dia" placeholder="dd" min="0" max="31" value="00">
                        </div>
                        <div class="grupo wauto">
                            <label for="mes" class="labels">Mes:</label>
                            <input type="number" class="inputs fecha" id="mes" name="mes" placeholder="mm" min="1" max="12" value="00">
                        </div>
                        <div class="grupo wauto">
                            <label for="agno" class="labels">Año:</label>
                            <input type="number" class="inputs fecha" id="agno" name="agno" placeholder="yyyy" min="2021" value="2021">
                        </div>
                    </div>

                </div><!-- Seccion para el area de texto y la fecha -->

                <div class="grupo">
                    <input type="hidden" name="notita" value="<%= id %>">
                    <input type="hidden" name="elemento" value="<%= index %>">
                    <input type="submit" class="enviar" id="agnadir" value="Editar">
                </div>
            </form>
        </div>
    </body>
</html>
