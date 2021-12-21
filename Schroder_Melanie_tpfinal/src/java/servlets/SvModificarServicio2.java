package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.ControladoraLogica;
import java.text.ParseException;
import javax.servlet.ServletException;
import logica.Servicio;

@WebServlet(name = "SvModificarServicio2", urlPatterns = {"/SvModificarServicio2"})
public class SvModificarServicio2 extends HttpServlet {

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

        //Traer codigo
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String destino = request.getParameter("destino");
        double costo = Double.parseDouble(request.getParameter("costo"));

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaAConvertir = request.getParameter("fecha");
        Date fecha = null;
        //Intento la conversion de fechas
        try {
            fecha = formato.parse(fechaAConvertir);
        } catch (ParseException ex) {
            Logger.getLogger(SvNewEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        ControladoraLogica controlLog = new ControladoraLogica();
        Servicio servicio = controlLog.buscarServicio(codigo);
        servicio.setNombreServicio(nombre);
        servicio.setDescripcionServicio(descripcion);
        servicio.setDestinoServicio(destino);
        servicio.setFechaServicio(fecha);
        servicio.setCostoServicio(costo);

        controlLog.modificarServicio(servicio);

        //actualizar
        request.getSession().setAttribute("listaServicios", controlLog.traerServicios());
        response.sendRedirect("tablaServicios.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
