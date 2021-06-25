package web;

import data.LoginDAO;
import domain.Encriptacion;
import domain.Login;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Recuperacion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter salida = response.getWriter();
        String usuarioRec = request.getParameter("usuario");
        String passRec = request.getParameter("pass");
        String passEncriptada = Encriptacion.encode(passRec);

        LoginDAO ldao = new LoginDAO();

        Login usrAntiguo = (Login) ldao.obtenerDatos(usuarioRec);
        if(usrAntiguo != null){
            int id = usrAntiguo.getId(); // recuperamos el id del usuario antiguo
            Login usrRecuperado = new Login(id, usuarioRec, passEncriptada);
            int fila = ldao.actualizar(usrRecuperado);
            if(fila > 0){
                salida.print("true");
            }else{
                salida.print("false");
            }
        }else{
            salida.print("false");
        }

    }
}
