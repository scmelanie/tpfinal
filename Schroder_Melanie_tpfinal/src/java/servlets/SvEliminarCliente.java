package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.ControladoraLogica;

@WebServlet(name = "SvEliminarCliente", urlPatterns = {"/SvEliminarCliente"})
public class SvEliminarCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    processRequest(request, response);
    }*/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Traer ID
        int id = Integer.parseInt(request.getParameter("id"));

        ControladoraLogica controlLog = new ControladoraLogica();

        
        controlLog.eliminarCliente(id);

        //Actualizar lista de personas
        request.getSession().setAttribute("listaClientes", controlLog.traerClientes());
        response.sendRedirect("tablaClientes.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
