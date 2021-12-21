package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Cliente;
import logica.ControladoraLogica;

@WebServlet(name = "SvModificarCliente", urlPatterns = {"/SvModificarCliente"})
public class SvModificarCliente extends HttpServlet {

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

        //Busco el cliente y creo un objeto con todos sus datos para pasarle al jsp
        Cliente cliente = controlLog.buscarCliente(id);

        //Traigo la sesion y guardo el objeto cliente completo, para redirigir al jsp cargado 
        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("cliente", cliente);
        response.sendRedirect("modificarCliente.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
