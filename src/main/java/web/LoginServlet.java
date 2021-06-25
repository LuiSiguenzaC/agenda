package web;

import data.AgendaDAO;
import data.LogDAO;
import data.LoginDAO;
import domain.Encriptacion;
import domain.Login;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=utf-8");
        PrintWriter salida = response.getWriter();

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("pass");

        String passwordEncriptada = Encriptacion.encode(password);

        Login login = new Login(usuario, passwordEncriptada);
        LoginDAO ldao = new LoginDAO();

        Login resultado = (Login) ldao.seleccionar(login);
        LogDAO log = new LogDAO();
        AgendaDAO adao = new AgendaDAO();

        if (resultado != null) { //si hay resultado en la busqueda, entonces se crea la sesión y se le asignan parámetros
            HttpSession sesion = request.getSession(true);
            int id = resultado.getId();
            String usuarioRecuperado = resultado.getUsuario();
            String fecha = log.obtenerFecha(id); //obtenemos la fecha de la última vez de inicio de sesión de este usuario
            List<Object> notas = (List<Object>) adao.seleccionarRelacion(resultado.getId());

            if (notas == null) {//si no hay notas asociadas
                notas = new ArrayList<>();
            }

            //agrega estos elementos a la sesión
            sesion.setAttribute("id", id);
            sesion.setAttribute("usuario", usuarioRecuperado);
            sesion.setAttribute("fecha", fecha);
            sesion.setAttribute("notas", notas);

            //response.sendRedirect("index.jsp");
            salida.print("true");
        } else {
            //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No hemos podido verificar tu identidad");
            salida.print("false");
        }

    }

}
