
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="logica.Venta"%>
<%@page import="java.util.List"%>
<%@page import="logica.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Lista de Ventas</title>
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
								<th class="column1">Código venta</th>
                                                                <th class="column2">Cliente</th>
								<th class="column2">Usuario</th>
								<th class="column3">Fecha</th>
                                                                <th class="column3">Código servicio</th>
								<th class="column3">Código paquete</th>
                                                                <th class="column3">Medio de Pago</th>
                                                                <th class="column3">Modificar</th>
                                                                <th class="column6">Eliminar</th>
							</tr>
						</thead>
						<tbody>
                                                    
                                                    <%  ControladoraLogica controlLog = new ControladoraLogica();
                                                        HttpSession miSesion = request.getSession();
                                                    
                                                    List <Venta> listaVentas = controlLog.traerVentas();
                                                    
                                                    for (Venta vt : listaVentas) {
                                                    %>  
                                                        <tr>
                                                            <% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                                            String fechaString = (vt.getFechaVenta()!=null) ? sdf.format(vt.getFechaVenta()):" ";
                                                            %>
                                                            
                                                            <% int codigo = vt.getNroVenta();%>
									<td class="column1"> <%=codigo%></td>
                                                            <% String nombreCompleto = vt.getCliente().getNombreCliente() + " " + vt.getCliente().getApellidoCliente();%>
									<td class="column2"><%=nombreCompleto%></td>
                                                            <% String usuario = vt.getUsuario().getNombreUsuario();%>
									<td class="column2"><%=usuario%></td>
                                                                        
                                                                        
                                                                        <td class="column3"><%=fechaString%></td>
                                                                        
                                                            <% if (vt.getServicio() != null ) {
                                                               int codServ = vt.getServicio().getCodigoServicio(); 
                                                               %>             <td class="column2"><%=codServ%></td>
                                                            <%} else {%>
                                                                            
                                                                               <td class="column3">-</td>
                                                            <%}%>
                                                            
									
                                                            
                                                                        
                                                            <% if (vt.getPaquete() != null ) {
                                                               int codPaq = vt.getPaquete().getCodigoPaquete(); 
                                                               %>             <td class="column2"><%=codPaq%></td>
                                                            <%} else {%>
                                                                            
                                                                               <td class="column3">-</td>
                                                            <%}%>
                                                                        
                                                            <% String medioPago = vt.getMedioPago();%>
									<td class="column3"><%=medioPago%></td>            
                                                                 
                                                                        <td class="column6">
                                                                            <form name="formModificarVenta" action="SvModificarVenta" method="POST" style="display:inline">
                                                                                <input type="hidden" name="codigo" value="<%=codigo%>">
                                                                                <button type="submit" class=" btn btn-primary" data-title="edit" style="display:inline">Modificar</button>
                                                                                
                                                                            </form>
                                                                        </td>
                                                                        
                                                                        <td class="column6">
                                                                            <form name="formEliminarVenta" action="SvEliminarVenta" method="POST" style="display:inline">
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

</body>
</html>
