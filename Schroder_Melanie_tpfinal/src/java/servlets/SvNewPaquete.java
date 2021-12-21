package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.ControladoraLogica;

@WebServlet(name = "SvNewPaquete", urlPatterns = {"/SvNewPaquete"})
public class SvNewPaquete extends HttpServlet {

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

        ControladoraLogica controlLog = new ControladoraLogica();

        //Recibo parametros y guardo en una variable para mandar a la logica 
        String[] servicioElegido = request.getParameterValues("servicio");

        controlLog.crearPaquete(servicioElegido);

        response.sendRedirect("tablaPaquetes.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
