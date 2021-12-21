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

@WebServlet(name = "SvNewVenta", urlPatterns = {"/SvNewVenta"})
public class SvNewVenta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /* @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    processRequest(request, response);
    }*/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Recibo parametros y guardo variables para mandar a la logica
        int servicio = Integer.parseInt(request.getParameter("servicioid"));
        int paquete = Integer.parseInt(request.getParameter("paqueteid"));
        int cliente = Integer.parseInt(request.getParameter("clienteid"));
        int usuario = Integer.parseInt(request.getParameter("usuarioid"));

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaAConvertir = request.getParameter("fecha");
        Date fecha = null;
        //Intento la conversion de fechas
        try {
            fecha = formato.parse(fechaAConvertir);
        } catch (ParseException ex) {
            Logger.getLogger(SvNewEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        String medioPago = request.getParameter("medioPago");

        //Llamado al metodo de la controladora de logica pasandole parametros
        ControladoraLogica controlLog = new ControladoraLogica();
        controlLog.crearVenta(servicio, paquete, cliente, usuario, fecha, medioPago);

        response.sendRedirect("tablaVentas.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
