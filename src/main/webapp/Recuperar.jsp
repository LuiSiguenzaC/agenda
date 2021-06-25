
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
    <head>
        <%@include file="WEB-INF/importes.html"%>
        <title>Recuperacion</title>
    </head>
    <body class="bg_body">
        <div class="contenedor">
            <section class="form_recuperacion">
                <h1 class="registro__titulo">Recuperar Contraseña</h1>
                <form class="formulario" id="formularioRecuperar">
                    <div class="grupo">
                        <label for="usuario" class="labels">Ingresa tu Usuario:</label>
                        <input type="text" id="usuario" name="usuario" placeholder="Tu usuario" class="inputs" required="true">
                    </div>
                    <div class="grupo">
                        <label for="pass" class="labels">Ingresa la nueva Contraseña:</label>
                        <input type="password" id="pass" name="pass" placeholder="Nueva Contraseña" class="inputs" required="true">
                    </div>
                    <div class="alineacion">
                        <div class="grupo">
                            <input class="enviar" type="submit" value="RECUPERAR">
                        </div>
                        <div class="grupo">
                            <div><a class="volver" class="link" href="Login.jsp">¡volver al login!&rarr;</a></div>
                        </div>
                    </div>
                </form>

            </section>
            <script src="js/jquery-3.6.0.min.js"></script>
            <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script src="js/alertaRecuperar.js"></script>
    </body>
</html>