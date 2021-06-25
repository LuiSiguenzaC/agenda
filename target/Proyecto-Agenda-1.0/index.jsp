<%@page import="java.sql.Connection"%>
<%@page import="data.Conexion"%>
<%@page import="domain.Agenda"%>
<%@page import="java.util.List"%>
<%@page import="data.AgendaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>

<%
    session = (HttpSession) request.getSession();
    if (session.getAttribute("usuario") == null && session.getAttribute("id") == null) {
        //si el atributo no existe en la sesión, se redirige a la página de login
        response.sendRedirect("Login.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/importes.html"%>
        <title>Agenda</title>
    </head>
    <body class="bg_body">
        <h1 class="cabecera-agenda">Bienvenido <%= (String) session.getAttribute("usuario")%></h1>
        <a href="CerrarSesion" class="cerrar_sesion">Cerrar Sesión</a>
        <%
            if (session.getAttribute("fecha") != null) { //si el usuario ya había iniciado sesión anteriormente
%>
        <div class="contenedor_fecha">
            <p class="ultima_conexion"><span>Última conexión:</span><%= (String) session.getAttribute("fecha")%></p>
        </div>
        <% } %>
        <div class="contenedor2">
            <form id="nuevaNota" class="formulario u-padding-top"><!-- Formulario para agregar una nueva nota -->
                <legend>Añadir una Nota <span>todos los campos obligatorios</span></legend>
                <div class="seccion">
                    <div class="grupo">
                        <label for="descripcion" class="labels">Descripción:</label>
                        <textarea class="inputs" name="descripcion" id="descripcion" rows="4" cols="50" placeholder="añade una descripción"></textarea>
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
                </div>

                <div class="grupo">
                    <input type="submit" class="enviar" value="Añadir">
                </div>
            </form>

            <div class="contendor2">
                <%
                    int totalNotas = 0;
                    if (session.getAttribute("usuario") != null) {
                        AgendaDAO agenda = new AgendaDAO();
                        int id = (Integer) session.getAttribute("id");
                        List<Object> lista1 = (List<Object>) agenda.seleccionarRelacion(id);
                        totalNotas = lista1.size();
                    }
                %>              
                <div class="contenedor-tabla formulario2">
                    <h2 class="notas"><span class="contador" id="contador"><%= totalNotas%></span>Notas</h2>
                    <table id="tabla-notas" class="tabla-notas">
                        <thead>
                            <tr>
                                <th>Descripción</th>
                                <th>Fecha</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody id ="notas">
                            <%
                                if (session.getAttribute("usuario") != null) {

                                    AgendaDAO agenda = new AgendaDAO();
                                    int id = (Integer) session.getAttribute("id");
                                    List<Object> lista2 = (List<Object>) agenda.seleccionarRelacion(id);
                                    int contador = 0;
                                    int index;
                                    for (Object obj : lista2) {
                                        Agenda nota = (Agenda) obj;
                                        String descripcion = nota.getDesccripcion();
                                        String fecha = nota.getFecha();
                                        int idnota = nota.getIdNota();
                                        index = contador++;
                            %>
                            <tr>
                                <td><%= descripcion%></td>
                                <td class="u-centrar"><%= fecha%></td>
                                <td class="u-centrar"> 
                                    <form action="Editar.jsp" method="GET" style="display: inline-block">
                                        <input type="hidden" name="index" value="<%= index%>">
                                        <input type="hidden" name="idnota" value="<%= idnota%>">
                                        <button type="submit" class="btn btn-editar"><i class="fas fa-pen-square"></i></button>
                                    </form>  
                                    <form id="eliminar" style="display: inline-block">
                                        <input type="hidden" id="index" name="index" >
                                        <input type="hidden" id="idnota" name="idnota" value="<%= idnota%>">
                                        <button type="button"  data-indice="<%= index%>" data-id="<%= idnota%>" id="btnEliminar" class="btn-borrar btn" class="btn btn-editar"><i class="fas fa-trash-alt"></i></button>
                                    </form>
                                </td>
                            </tr>
                            <% }
                                }
                            %>
                        </tbody>
                    </table>
                </div>      
            </div>
        </div>
        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="js/app.js"></script>
    </body>
</html>
