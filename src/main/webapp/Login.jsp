<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
    <head>
        <%@include file="WEB-INF/importes.html"%>
        <title>Login</title>
    </head>
    <body class="bg_body">
        <div class="contenedor">
            <section class="form_Login">
                <h1 class="registro__titulo">BIENVENIDO</h1>
                <form class="formulario" id="formulariLogin" action="LoginServlet" method="POST">
                    <div class="grupo">
                        <label for="usuario" class="labels">Usuario:</label>
                        <input type="text" name="usuario" class="inputs" id="usuario" placeholder="Ingresa tu nombre de usuario" required>
                    </div>

                    <div class="grupo">
                        <label for="pass" class="labels">Contraseña:</label>
                        <input type="password" name="pass" class="inputs" id="pass" placeholder="Ingresa tu contraseña" required>
                    </div>

                    <div class="alineacion">
                        <div class="grupo">
                            <input type="submit" class="enviar" value="Ingresar">
                        </div>
                        <div class="grupo">
                            <a href="Recuperar.jsp" class="link volver">Olvidé mi contraseña</a> 
                        </div>
                        <div class="grupo">
                            <a href="Registro.jsp" class="link">Registrate!</a> 
                        </div>
                    </div>
                </form>
            </section>  
        </div>
        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="js/alertaLogin.js"></script>
    </body>
</html>
