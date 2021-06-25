package web;

import data.AgendaDAO;
import data.Conexion;
import domain.Agenda;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class EditarNota extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter salida = response.getWriter();

        HttpSession misesion = (HttpSession) request.getSession();

        List<Object> notas = (List<Object>) misesion.getAttribute("notas");

        int idnota = Integer.parseInt(request.getParameter("notita"));
        int index = Integer.parseInt(request.getParameter("elemento"));
        String descripcion = request.getParameter("descripcion");
        String dia = request.getParameter("dia");
        String mes = request.getParameter("mes");
        String agno = request.getParameter("agno");
        String fecha = agno + "/" + mes + "/" + dia;

        AgendaDAO agenda = new AgendaDAO();

        Agenda nota1 = new Agenda(descripcion, fecha, idnota);

        agenda.actualizar(nota1); //se actualiza la nota en la base de datos;

        notas.remove(index); //se elimina el elemento de la lista

        //notas.add(nota1); se agrega a la lista la nota actualizada
        notas.add(index, nota1); //se agrega a la lista la nota actualizada

        response.sendRedirect("index.jsp");

    }

}
