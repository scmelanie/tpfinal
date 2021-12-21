<%@page import="java.text.SimpleDateFormat"%>
<%@page import="logica.Servicio"%>
<%@page import="java.util.List"%>
<%@page import="logica.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Lista de Servicios</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="carpetasTablas/images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="carpetasTablas/vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="carpetasTablas/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="carpetasTablas/vendor/animate/animate.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="carpetasTablas/vendor/select2/select2.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="carpetasTablas/vendor/perfect-scrollbar/perfect-scrollbar.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="carpetasTablas/css/util.css">
        <link rel="stylesheet" type="text/css" href="carpetasTablas/css/main.css">
        <!--===============================================================================================-->
    </head>
    <body>
        <%
            HttpSession miSesion = request.getSession();
            String usuario = (String) miSesion.getAttribute("usuario");
            if (usuario == null) {
                response.sendRedirect("login.jsp");
            } else {

        %>

        <div class="limiter">
            <div class="container-table100">
                <div class="wrap-table100">
                    <div class="table100">
                        <table>
                            <thead>
                                <tr class="table100-head">
                                    <th class="column1">Nombre</th>
                                    <th class="column2">Código servicio</th>
                                    <th class="column2">Descripción</th>
                                    <th class="column2">Destino</th>
                                    <th class="column3">Fecha</th>
                                    <th class="column3">Costo</th>
                                    <th class="column3">Modificar</th>
                                    <th class="column6">Eliminar</th>
                                </tr>
                            </thead>
                            <tbody>

                                <%  ControladoraLogica controlLog = new ControladoraLogica();
                                    HttpSession sesion = request.getSession();

                                    List<Servicio> listaServicios = controlLog.traerServicios();

                                    for (Servicio srv : listaServicios) {
                                %>  
                                <tr>
                                    <% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                        String fechaString = (srv.getFechaServicio() != null) ? sdf.format(srv.getFechaServicio()) : " ";
                                    %>

                                    <% String nombre = srv.getNombreServicio();%>
                                    <td class="column1"> <%=nombre%></td>
                                    <% int codigo = srv.getCodigoServicio();%>
                                    <td class="column2"><%=codigo%></td>
                                    <% String descripcion = srv.getDescripcionServicio();%>
                                    <td class="column2"><%=descripcion%></td>

                                    <% String destino = srv.getDestinoServicio();%>
                                    <td class="column2"><%=destino%></td>

                                    <td class="column3"><%=fechaString%></td>
                                    <% double costo = srv.getCostoServicio();%>
                                    <td class="column3"><%=costo%></td>

                                    <td class="column6">
                                        <form name="formModificarServicio" action="SvModificarServicio" method="POST" style="display:inline">
                                            <input type="hidden" name="codigo" value="<%=codigo%>">
                                            <button type="submit" class=" btn btn-primary" data-title="edit" style="display:inline">Modificar</button>

                                        </form>
                                    </td>

                                    <td class="column6">
                                        <form name="formEliminarServicio" action="SvEliminarServicio" method="POST" style="display:inline">
                                            <input type="hidden" name="codigo" value="<%=codigo%>">
                                            <button type="submit" class=" btn btn-danger" data-title="delete" style="display:inline">Eliminar</button>

                                        </form>
                                    </td>


                                </tr>

                                <%}%>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>




        <!--===============================================================================================-->	
        <script src="carpetasTablas/vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="carpetasTablas/vendor/bootstrap/js/popper.js"></script>
        <script src="carpetasTablas/vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="carpetasTablas/vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="carpetasTablas/js/main.js"></script>
        <%}%>
    </body>
</html>
