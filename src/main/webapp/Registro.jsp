<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/importes.html"%>
        <title>Registro</title>
    </head>
    <body class="bg_body">
        <div class="contenedor">
            <h1 class="registro__titulo">Regístrate</h1>
            <!--action="RegistroServlet" method="POST" -->
            <form id="formularioRegistro" class="formulario"><!-- formulario para registrar a un nuevo usuario a la base de datos -->
                <div class="seccion">
                    <div class="grupo division">
                        <label for="nombre" class="labels">Nombre:</label>
                        <input type="text" name="nombre" id="nombre" class ="inputs" placeholder="Tu nombre" required="true">
                    </div>
                    <div class="grupo">
                        <label for="apellido" class="labels">Apellido:</label>
                        <input type="text" name="apellido" id="apellido" class="inputs" placeholder="Tu apellido" required="true">
                    </div>
                </div><!-- Sección para nombre y apellido -->

                <div class="seccion">
                    <div class="grupo">
                        <label for="telefono" class="labels">Teléfono:</label>
                        <input type="tel" name="telefono" id="telefono" class="inputs" placeholder="Tu teléfono" required="true">
                    </div>
                    <div class="grupo">
                        <label for="direccion" class="labels">Dirección:</label>
                        <input type="text" name="direccion" id="direccion" class="inputs" placeholder="Tu dirección" required="true">
                    </div>
                </div><!-- Sección para teléfono y dirección -->

                <div class="seccion">
                    <div class="grupo">
                        <label for="usuario" class="labels">Usuario:</label>
                        <input type="text" name="usuario" id="usuario" class="inputs" placeholder="Tu usuario" required="true">
                    </div>
                    <div class="grupo">
                        <label for="pass" class="labels">Contraseña:</label>
                        <input type="password" name="pass" id="pass" class="inputs" placeholder="Contraseña" required="true">
                    </div>
                </div><!-- Sección para usuario y contraseña -->
                <div class="grupo alineacion">
                    <input type="submit" class="enviar" id="enviar" value="Registrarme">
                    <a href="Login.jsp" class="volver">Ya tengo una cuenta &rarr;</a>
                </div>

            </form>
        </div>
        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="js/alertasRegistro.js"></script>
    </body>
</html>
