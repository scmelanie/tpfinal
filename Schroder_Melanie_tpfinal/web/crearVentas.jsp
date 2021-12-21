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
        <title>Registro de Ventas</title>

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
                        <h3 class="title">Añadir nueva venta</h3>
                    </div>
                    <div class="card-body">
                        <form action="SvNewVenta" method="POST">
                            <div class="form-row">
                                <div class="name">Código del servicio</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="servicioid">
                                        <small>Si el producto es un PAQUETE, ingrese 0</small>
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Código del paquete</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="paqueteid">
                                        <small>Si el producto es un SERVICIO, ingrese 0</small>

                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">ID del cliente</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="clienteid">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">ID del usuario</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="usuarioid">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Fecha de venta</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="fecha" placeholder="dd/mm/aaaa">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Medio de pago</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="medioPago">
                                    </div>
                                </div>
                            </div>
                            <div>
                                <button class="btn btn--radius-2 btn--green" type="submit">Añadir Venta</button>
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
