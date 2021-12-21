package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.ControladoraLogica;
import logica.Venta;

@WebServlet(name = "SvModificarVenta", urlPatterns = {"/SvModificarVenta"})
public class SvModificarVenta extends HttpServlet {

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
        
         //Traer codigo
        int codigo = Integer.parseInt(request.getParameter("codigo"));

        ControladoraLogica controlLog = new ControladoraLogica();

        //Busco y creo un objeto con todos sus datos para pasarle al jsp
        Venta venta = controlLog.buscarVenta(codigo);

        //Traigo la sesion y guardo el objeto completo, para redirigir al jsp cargado 
        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("venta", venta);
        response.sendRedirect("modificarVenta.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
