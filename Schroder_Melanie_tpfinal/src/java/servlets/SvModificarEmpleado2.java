package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.ControladoraLogica;
import logica.Empleado;

@WebServlet(name = "SvModificarEmpleado2", urlPatterns = {"/SvModificarEmpleado2"})
public class SvModificarEmpleado2 extends HttpServlet {

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
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");
        String direccion = request.getParameter("direccion");
        String nacionalidad = request.getParameter("nacionalidad");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String cargo = request.getParameter("cargo");
        double sueldo = Double.parseDouble(request.getParameter("sueldo"));

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaAConvertir = request.getParameter("fechaNac");
        Date fechaNac = null;
        //Intento la conversion de fechas
        try {
            fechaNac = formato.parse(fechaAConvertir);
        } catch (ParseException ex) {
            Logger.getLogger(SvNewEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        ControladoraLogica controlLog = new ControladoraLogica();
        Empleado empleado = controlLog.buscarEmpleado(id);
        empleado.setNombreEmpleado(nombre);
        empleado.setApellidoEmpleado(apellido);
        empleado.setDniEmpleado(dni);
        empleado.setDireccionEmpleado(direccion);
        empleado.setEmailEmpleado(email);
        empleado.setNacionalidadEmpleado(nacionalidad);
        empleado.setCelularEmpleado(telefono);
        empleado.setCargoEmpleado(cargo);
        empleado.setSueldoEmpleado(sueldo);
        empleado.setFechaNacEmpleado(fechaNac);

        controlLog.modificarEmpleado(empleado);

        //actualizar
        request.getSession().setAttribute("listaEmpleados", controlLog.traerEmpleados());
        response.sendRedirect("tablaEmpleados.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
