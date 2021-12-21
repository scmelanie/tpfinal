<%@page import="logica.Usuario"%>
<%@page import="java.util.Date"%>
<%@page import="logica.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="logica.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Lista de Usuarios</title>
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
	
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100">
					<table>
						<thead>
							<tr class="table100-head">
								<th class="column1">Nombre</th>
								<th class="column2">ID Empleado</th>
								<th class="column2">ID Usuario</th>
								<th class="column3">Usuario</th>
                                                                <th class="column3">Modificar</th>
                                                                <th class="column6">Eliminar</th>
								
							</tr>
						</thead>
						<tbody>
                                                        
                                                    <%  ControladoraLogica controlLog = new ControladoraLogica();
                                                        HttpSession miSesion = request.getSession();
                                                    
                                                    List <Empleado> listaEmpleados = controlLog.traerEmpleados();
                                                    
                                                    for (Empleado emple : listaEmpleados) {
                                                    %>  
                                                        <tr>
                                                            
                                                            <% String nombreCompleto = emple.getNombreEmpleado() + " " + emple.getApellidoEmpleado();%>
									<td class="column1"> <%=nombreCompleto%></td>
                                                            <% int id = emple.getIdEmpleado();%>
									<td class="column2"><%=id%></td>
                                                            <% int idU = emple.getUsuario().getIdUsuario();%>
									<td class="column2"><%=idU%></td>
                                                            <% String usuario = emple.getUsuario().getNombreUsuario();%>
									<td class="column3"><%=usuario%></td>
                                                                        
                                                                        
                                                                        <td class="column3">
                                                                            <form name="formModificarUsuario" action="SvModificarUsuario" method="POST" style="display:inline">
                                                                                <input type="hidden" name="id" value="<%=id%>">
                                                                                <button type="submit" class=" btn btn-primary" data-title="edit" style="display:inline">Modificar</button>
                                                                                
                                                                            </form>
                                                                        </td>
                                                                        
                                                                        <td class="column6">
                                                                            <form name="formEliminarUsuario" action="SvEliminarUsuario" method="POST" style="display:inline">
                                                                                <input type="hidden" name="id" value="<%=id%>">
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

</body>
</html>