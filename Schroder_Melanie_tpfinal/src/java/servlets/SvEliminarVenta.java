
package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.ControladoraLogica;
import logica.Venta;

@WebServlet(name = "SvEliminarVenta", urlPatterns = {"/SvEliminarVenta"})
public class SvEliminarVenta extends HttpServlet {

    
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
        processRequest(request, response);
        
        //Traer ID
        int codigo = Integer.parseInt(request.getParameter("codigo"));

        ControladoraLogica controlLog = new ControladoraLogica();

        //Buscar registros asociado y eliminarlos
        Venta venta = controlLog.buscarVenta(codigo);
        
        if (venta.getCliente()!= null) {
            int id= venta.getCliente().getIdCliente();
            controlLog.eliminarCliente(id);
        }
        if (venta.getUsuario()!= null) {
            int id= venta.getUsuario().getIdUsuario();
            controlLog.eliminarUsuario(id);
        }
        if (venta.getPaquete()!= null) {
            int id= venta.getPaquete().getCodigoPaquete();
            controlLog.eliminarPaquete(id);
        }
        if (venta.getServicio()!= null) {
            int id= venta.getServicio().getCodigoServicio();
            controlLog.eliminarServicio2(id);
        }

        //Eliminar mi objeto de interes inicial
        controlLog.eliminarVenta(codigo);

        //Actualizar lista
        request.getSession().setAttribute("listaVentas", controlLog.traerVentas());
        response.sendRedirect("tablaVentas.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
