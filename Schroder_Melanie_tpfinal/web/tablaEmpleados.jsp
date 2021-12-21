<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="logica.ControladoraLogica"%>
<%@page import="logica.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Lista de Empleados</title>
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
        HttpSession sesion = request.getSession();
        String usuario = (String) sesion.getAttribute("usuario");
        if(usuario == null) {
           response.sendRedirect("login.jsp");
        }else{
            
    %>
	
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100">
					<table>
						<thead>
							<tr class="table100-head">
								<th class="column1">Nombre</th>
								<th class="column2">ID Empleado</th>
								<th class="column2">DNI</th>
								<th class="column3">Fecha de Nacimiento</th>
                                                                <th class="column3">Dirección</th>
								<th class="column3">Nacionalidad</th>
								<th class="column3">Email</th>
                                                                <th class="column3">Teléfono</th>
                                                                <th class="column3">Cargo</th>
                                                                <th class="column3">Sueldo</th>
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
                                                             <% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                                            String fechaString = (emple.getFechaNacEmpleado()!=null) ? sdf.format(emple.getFechaNacEmpleado()):" ";
                                                            %>
                                                            
                                                          
                                                            <% String nombreCompleto = emple.getNombreEmpleado() + " " + emple.getApellidoEmpleado();%>
									<td class="column1"> <%=nombreCompleto%></td>
                                                            <% int id = emple.getIdEmpleado();%>
									<td class="column2"><%=id%></td>
                                                            <% String dni = emple.getDniEmpleado();%>
									<td class="column2"><%=dni%></td>
                                                                      
									<td class="column3"><%=fechaString%></td>
                                                            <% String direccion = emple.getDireccionEmpleado();%>
									<td class="column3"><%=direccion%></td>
                                                            <% String nacionalidad = emple.getNacionalidadEmpleado();%>
									<td class="column3"><%=nacionalidad%></td>
                                                            <% String email = emple.getEmailEmpleado();%>
                                                                        <td class="column3"><%=email%></td>
                                                            <% String telefono = emple.getCelularEmpleado();%>
                                                                        <td class="column3"><%=telefono%></td>
                                                            <%String cargo = emple.getCargoEmpleado();%>
                                                                        <td class="column3"><%=cargo%></td>
                                                            <% double sueldo = emple.getSueldoEmpleado();%>
                                                                        <td class="column3"><%=sueldo%></td>
                                                                        
                                                                        <td class="column6">
                                                                            <form name="formModificarEmpleado" action="SvModificarEmpleado" method="POST" style="display:inline">
                                                                                <input type="hidden" name="id" value="<%=id%>">
                                                                                <button type="submit" class=" btn btn-primary" data-title="edit" style="display:inline">Modificar</button>
                                                                                
                                                                            </form>
                                                                        </td>
                                                                        
                                                                        <td class="column6">
                                                                            <form name="formEliminarEmpleado" action="SvEliminarEmpleado" method="POST" style="display:inline">
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

        <%}%>
</body>
</html>