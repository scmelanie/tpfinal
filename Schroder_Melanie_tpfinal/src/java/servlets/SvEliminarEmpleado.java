package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.ControladoraLogica;
import logica.Empleado;

@WebServlet(name = "SvEliminarEmpleado", urlPatterns = {"/SvEliminarEmpleado"})
public class SvEliminarEmpleado extends HttpServlet {

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

        //Buscar registros asociado y eliminarlos
        Empleado empleado = controlLog.buscarEmpleado(id);
        if (empleado.getUsuario() != null) {
            int idUsuario = empleado.getUsuario().getIdUsuario();
            controlLog.eliminarUsuario2(idUsuario);
        }

        //Eliminar mi objeto de interes inicial
        controlLog.eliminarEmpleado(id);

        //Actualizar lista de personas
        request.getSession().setAttribute("listaEmpleados", controlLog.traerEmpleados());
        response.sendRedirect("tablaEmpleados.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
