<%@page import="java.text.SimpleDateFormat"%>
<%@page import="logica.Cliente"%>
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
        <title>Modificación de Clientes</title>

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
                        <h3 class="title">Editar cliente</h3>
                    </div>
                    <div class="card-body">
                        <form action="SvModificarCliente2" method="POST">

                            <% HttpSession sesion = request.getSession();
                            Cliente cliente = (Cliente) miSesion.getAttribute("cliente");
                            {%>


                            <div class="form-row m-b-55">
                                <div class="name">Nombre</div>
                                <div class="value">
                                    <div class="row row-space">
                                        <div class="col-2">
                                            <div class="input-group-desc">
                                                <input class="input--style-5" type="text" name="nombre" value="<%=cliente.getNombreCliente()%>">
                                                <label class="label--desc">Nombre</label>
                                            </div>
                                        </div>
                                        <div class="col-2">
                                            <div class="input-group-desc">
                                                <input class="input--style-5" type="text" name="apellido" value="<%=cliente.getApellidoCliente()%>">
                                                <label class="label--desc">Apellido</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Dirección</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="direccion" value="<%=cliente.getDireccionCliente()%>">
                                    </div>
                                </div>
                            </div>

                            <% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                String fechaString = (cliente.getFechaNacCliente() != null) ? sdf.format(cliente.getFechaNacCliente()) : " ";

                            %>       

                            <div class="form-row">
                                <div class="name">Fecha de Nacimiento</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="fechaNac" value="<%=fechaString%>" placeholder="dd/mm/aaaa">
                                        <small>Formato dd/mm/aaaa</small>
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">DNI</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="dni" value="<%=cliente.getDniCliente()%>">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Nacionalidad</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="nacionalidad" value="<%=cliente.getNacionalidadCliente()%>">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Email</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="email" name="email" value="<%=cliente.getEmailCliente()%>">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Teléfono</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="telefono" value="<%=cliente.getCelularCliente()%>">
                                    </div>
                                </div>
                            </div>


                            <input class="input--style-5" type="hidden" name="id" value="<%=cliente.getIdCliente()%>">      


                            <div>
                                <button class="btn btn--radius-2 btn--blue" type="submit" >Guardar Cambios</button>
                            </div>
                            <%}%>
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
