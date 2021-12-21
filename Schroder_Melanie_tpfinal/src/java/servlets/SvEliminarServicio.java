package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.ControladoraLogica;

@WebServlet(name = "SvEliminarServicio", urlPatterns = {"/SvEliminarServicio"})
public class SvEliminarServicio extends HttpServlet {

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
        int codigo = Integer.parseInt(request.getParameter("codigo"));

        ControladoraLogica controlLog = new ControladoraLogica();
        controlLog.eliminarServicio(codigo);

        //Actualizar lista de personas
        request.getSession().setAttribute("listaServicios", controlLog.traerServicios());
        response.sendRedirect("tablaServicios.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
