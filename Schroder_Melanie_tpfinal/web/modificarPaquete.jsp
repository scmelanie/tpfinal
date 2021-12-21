<%@page import="logica.ControladoraLogica"%>
<%@page import="logica.Servicio"%>
<%@page import="java.util.List"%>
<%@page import="logica.Paquete"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Colorlib Templates">
        <meta name="author" content="Colorlib">
        <meta name="keywords" content="Colorlib Templates">

        <!-- Title Page-->
        <title>Modificación de Paquetes</title>

        <!-- Icons font CSS-->
        <link href="carpetasForms/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
        <link href="carpetasForms/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <!-- Font special for pages-->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">

        <!-- Vendor CSS-->
        <link href="carpetasForms/vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="carpetasForms/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="carpetasForms/css/main.css" rel="stylesheet" media="all">
    </head>

    <body>
        <%
            HttpSession miSesion = request.getSession();
            String usuario = (String) miSesion.getAttribute("usuario");
            if (usuario == null) {
                response.sendRedirect("login.jsp");
            } else {

        %>
        <div class="page-wrapper bg-gra-03 p-t-45 p-b-50">
            <div class="wrapper wrapper--w790">
                <div class="card card-5">
                    <div class="card-heading">
                        <h3 class="title">Editar paquete</h3>
                    </div>
                    <div class="card-body">
                        <form action="SvModificarPaquete2" method="POST">

                            <% ControladoraLogica controlLog = new ControladoraLogica();
                                HttpSession sesion = request.getSession();
                                Paquete paquete = (Paquete) miSesion.getAttribute("paquete");

                                List<Servicio> servicios = controlLog.traerServicios();
                                List<Servicio> serviciosPorPaquete = paquete.getListaServiciosPorPaquete();

                                for (Servicio srv : servicios) {

                                    String nombre = srv.getNombreServicio() + " en " + srv.getDestinoServicio();%>

                            <%int codigo = srv.getCodigoServicio();%>


                            <input type="checkbox" name="nombre" id="nombre" value="<%=nombre%>"> 
                            <label for="nombre"><%=nombre%></label> 

                            <%  boolean marcado = false;
                                for (Servicio servicioMarcado : serviciosPorPaquete) {
                                    if (srv.getCodigoServicio() == servicioMarcado.getCodigoServicio()) {
                                        marcado = true;
                                        break;
                                    }
                                }%>
                            <input class="input--style-5" type="hidden" name="codigo" value="<%=paquete.getCodigoPaquete()%>">




                            <%}%>




                            <div>
                                <button class="btn btn--radius-2 btn--blue" type="submit">Guardar Cambios</button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Jquery JS-->
        <script src="carpetasForms/vendor/jquery/jquery.min.js"></script>
        <!-- Vendor JS-->
        <script src="carpetasForms/vendor/select2/select2.min.js"></script>
        <script src="carpetasForms/vendor/datepicker/moment.min.js"></script>
        <script src="carpetasForms/vendor/datepicker/daterangepicker.js"></script>

        <!-- Main JS-->
        <script src="carpetasForms/js/global.js"></script>
        <%}%>
    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->
