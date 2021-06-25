package web;

import data.AgendaDAO;
import data.Conexion;
import domain.Agenda;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AgendaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter salida = response.getWriter();

        String descripcion = request.getParameter("descripcion");
        String dia = request.getParameter("dia");
        String mes = request.getParameter("mes");
        String agno = request.getParameter("agno");
        String fecha = agno + "/" + mes + "/" + dia;

        AgendaDAO agenda = new AgendaDAO();

        HttpSession misesion = (HttpSession) request.getSession();

        int id = (Integer) misesion.getAttribute("id");

        Agenda nota = new Agenda(id, descripcion, fecha);

        agenda.insertar(nota); //inserta la nota a la base de datos

        List<Object> notas = agenda.seleccionarRelacion(id);

        String html = "";
        int contador = 0;
        int index;
        for (Object obj : notas) {
            Agenda minota = (Agenda) obj;
            index = contador++;
            html += "<tr>"
                    + "<td>" + minota.getDesccripcion() + "</td>"
                    + "<td class=\"u-centrar\">" + minota.getFecha() + "</td>"
                    + "<td class = \"u-centrar\">"
                    + "<form action=\"Editar.jsp\" method=\"GET\" style=\"display: inline-block\">"
                    + "<input type=\"hidden\" name=\"index\" value=\"" + index + "\">"
                    + "<input type=\"hidden\" name=\"idnota\" value=\"" + minota.getIdNota() + "\">"
                    + "<button type=\"submit\" class=\"btn btn-editar\"><i class=\"fas fa-pen-square\"></i></button>"
                    + "</form>"
                    + "<form id=\"eliminar\" style=\"display: inline-block\">"
                    + "<input type=\"hidden\" id=\"index\" name=\"index\" value=\"" + index + "\">"
                    + "<input type=\"hidden\" id=\"idnota\" name=\"idnota\" value=\"" + minota.getIdNota() + "\">"
                    + "<button type=\"button\"  data-indice=\"" + index + "\" data-id=\"" + minota.getIdNota() + "\"id=\"btnEliminar\" class=\"btn-borrar btn\" class=\"btn btn-editar\"><i class=\"fas fa-trash-alt\"></i></button>"
                    + "</form>"
                    + "</td>"
                    + "</tr>";
        }

        //Conexion.cerrarConexion(conexion);
        salida.print(html);

    }

}
