package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.ControladoraLogica;
import logica.Empleado;

@WebServlet(name = "SvConsultaUsuarios", urlPatterns = {"/SvConsultaUsuarios"})
public class SvConsultaUsuarios extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Instancio la controladora de logica
        ControladoraLogica controlLog = new ControladoraLogica();

        //traer una lista
        List<Empleado> listaUsuarios = controlLog.traerUsuarios();

        //crear variable para asignar una sesion
        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("listaUsuarios", listaUsuarios);
        response.sendRedirect("tablaUsuarios.jsp");
    }

    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }*/
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
