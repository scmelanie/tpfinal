
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
import logica.Venta;

@WebServlet(name = "SvModificarVenta2", urlPatterns = {"/SvModificarVenta2"})
public class SvModificarVenta2 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    /* @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    
    }*/
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Traer datos
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        int codigoServ = Integer.parseInt(request.getParameter("servicioid"));
        int codigoPaq = Integer.parseInt(request.getParameter("paqueteid"));
        int idCliente = Integer.parseInt(request.getParameter("clienteid"));
        int usuarioid = Integer.parseInt(request.getParameter("usuarioid"));
        
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
        
        
        ControladoraLogica controlLog = new ControladoraLogica();
        Venta venta = controlLog.buscarVenta(codigo);
        venta.setServicio(controlLog.buscarServicio(codigoServ));
        venta.setPaquete(controlLog.buscarPaquete(codigoPaq));
        venta.setCliente(controlLog.buscarCliente(idCliente));
        venta.setUsuario(controlLog.buscarUsuario(usuarioid));
        venta.setFechaVenta(fecha);
        venta.setMedioPago(medioPago);
        
        controlLog.modificarVenta(venta);
        
        //actualizar
        request.getSession().setAttribute("listaVentas", controlLog.traerVentas());
        response.sendRedirect("tablaVentas.jsp");
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
