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

public class BorrarNota extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int elemento = Integer.parseInt(request.getParameter("index"));
        int idnota = Integer.parseInt(request.getParameter("idnota"));
        HttpSession misesion = (HttpSession) request.getSession(); //se obtiene la sesion 
        PrintWriter salida = response.getWriter();

        AgendaDAO agenda = new AgendaDAO();

        Agenda nota1 = new Agenda(idnota);

        int fila = agenda.borrar(nota1); //se elimina la nota de la base de datos

        if (fila > 0) {
            int id = (Integer) misesion.getAttribute("id");
            List<Object> notas = agenda.seleccionarRelacion(id);

            String html = "";
            int contador = 0;
            int index;
            for (Object obj : notas) { //vuelve a recorrer la lista de notas
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
            salida.print(html);
        }

    }

}
