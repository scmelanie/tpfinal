package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import logica.ControladoraLogica;

@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

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

        //Creo variables para almacenar los datos ingresados por interfaz
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");

        ControladoraLogica controlLog = new ControladoraLogica();

        //Creo un boolean para aplicar el metodo de verifacion de usuarios
        boolean verificado = controlLog.verificarUsuario(usuario, contra);

        //Establezco condiciones, si se verifica, redirecciono a la pagina principal
        if (verificado == true) {
            HttpSession miSesion = request.getSession(true);
            miSesion.setAttribute("usuario", usuario);
            miSesion.setAttribute("contra", contra);

            response.sendRedirect("principal.jsp");

        } else {
            //sino, redirecciono al login otra vez
            response.sendRedirect("login.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
