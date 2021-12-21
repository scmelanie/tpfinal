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
import logica.Cliente;
import logica.ControladoraLogica;

@WebServlet(name = "SvModificarCliente2", urlPatterns = {"/SvModificarCliente2"})
public class SvModificarCliente2 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /*
    @Override
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
        Cliente cliente = controlLog.buscarCliente(id);
        cliente.setNombreCliente(nombre);
        cliente.setApellidoCliente(apellido);
        cliente.setDniCliente(dni);
        cliente.setDireccionCliente(direccion);
        cliente.setNacionalidadCliente(nacionalidad);
        cliente.setEmailCliente(email);
        cliente.setCelularCliente(telefono);
        cliente.setFechaNacCliente(fechaNac);

        controlLog.modificarCliente(cliente);

        //actualizar
        request.getSession().setAttribute("listaClientes", controlLog.traerClientes());
        response.sendRedirect("tablaClientes.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
