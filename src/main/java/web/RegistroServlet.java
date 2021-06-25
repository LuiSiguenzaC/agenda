package web;

import data.LoginDAO;
import data.UsuarioDAO;
import domain.Encriptacion;
import domain.Login;
import domain.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter salida = response.getWriter();
        //recuperando los datos para crear un nuevo usuario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        
        //campos que pertenecen a la clase Login
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("pass");
        String passwordEncriptada =  Encriptacion.encode(password); //se encripta la contraseña
        
        Usuario nuevoUsuario = new Usuario(nombre, apellido, telefono,direccion);
        Login credenciales = new Login(usuario,passwordEncriptada);
        
        UsuarioDAO udao = new UsuarioDAO();
        LoginDAO ldao = new LoginDAO();
        
        int filaUsuario = udao.insertar(nuevoUsuario);
        int filaCredenciales = ldao.insertar(credenciales);
        
        if(filaUsuario >0 && filaCredenciales > 0){
            salida.print("true"); //envía esta respuesta a Ajax
        }else{
            salida.print("false");
        }
    }
}
