package web;

import data.AgendaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ContarNotas extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter salida = response.getWriter();
        String texto = request.getParameter("text");
        HttpSession misesion = (HttpSession) request.getSession();
        AgendaDAO agenda = new AgendaDAO();
        int id = (Integer) misesion.getAttribute("id");
        List<Object> lista1 = (List<Object>) agenda.seleccionarRelacion(id);

        int totalNotas = lista1.size();

        salida.print(totalNotas);

    }

}
