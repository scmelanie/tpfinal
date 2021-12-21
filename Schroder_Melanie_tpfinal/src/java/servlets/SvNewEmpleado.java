package servlets;

import java.io.IOException;
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

@WebServlet(name = "SvNewEmpleado", urlPatterns = {"/SvNewEmpleado"})
public class SvNewEmpleado extends HttpServlet {

    //Llamado global a la controladora de logica para usarla en el doPost y en el doGet por igual
    ControladoraLogica controlLog = new ControladoraLogica();

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

        //recibo parametros y guardo variables para mandar a la logica
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");

        //Para tratar la fecha creo variables locales para almacenar el dato String
        //y una de tipo Date, vacia en un principio, para guardar la conversion
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaAConvertir = request.getParameter("fechaNac");
        Date fechaNac = null;
        //Intento la conversion de fechas
        try {
            fechaNac = formato.parse(fechaAConvertir);
        } catch (ParseException ex) {
            Logger.getLogger(SvNewEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        String direccion = request.getParameter("direccion");
        String nacionalidad = request.getParameter("nacionalidad");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String cargo = request.getParameter("cargo");
        Double sueldo = Double.parseDouble(request.getParameter("sueldo"));
        String usuario = request.getParameter("nombreUsuario");
        String contra = request.getParameter("contra");

        //Llamado al metodo de la controladora de logica pasandole parametros
        controlLog.crearEmpleado(nombre, apellido, dni, fechaNac, direccion, nacionalidad, email, telefono, cargo, sueldo, usuario, contra);

        response.sendRedirect("tablaEmpleados.jsp");
        //PODRIA INTENTAR MANDAR A UNA TABLA DE EMPLEADOS

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
