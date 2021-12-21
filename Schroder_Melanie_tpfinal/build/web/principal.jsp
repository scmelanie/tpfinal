<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="icon" type="image/png" href="images/favicon-32x32.png" sizes="32x32" />
  <link rel="icon" type="image/png" href="images/favicon-16x16.png" sizes="16x16" />
  <title>Reisen - Agencia de Turismo</title>
  <!--stylesheet-->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700,900" rel="stylesheet">
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link href="styles/styles.css" rel="stylesheet" type="text/css">
  <link href="styles/custom-responsive-styles.css" rel="stylesheet" type="text/css">
  <!--scripts-->
  <script type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
  <script type="text/javascript" src="scripts/all-plugins.js"></script>
  <script type="text/javascript" src="scripts/plugins-activate.js"></script>
  
</head>

<body id="page-top">
    
    <% 
        HttpSession miSesion = request.getSession();
        String usuario = (String) miSesion.getAttribute("usuario");
        if(usuario == null) {
           response.sendRedirect("login.jsp");
        }else{
            
    %>
    
  <!-- Navigation -->
  <div class="logo">
    <i class="fa fa-plane" aria-hidden="true"><span>Reisen</span></i>
  </div>
  <a class="menu-toggle rounded" href="#">
    <i class="fa fa-bars"></i>
  </a>
  <nav id="sidebar-wrapper">
    <ul class="sidebar-nav">
      <li class="sidebar-brand">
        <a class="smooth-scroll" href="#Header"></a>
      </li>
      <li class="sidebar-nav-item">
        <a class="smooth-scroll" href="#About">Usuarios</a>
      </li>
      <li class="sidebar-nav-item">
        <a class="smooth-scroll" href="#Services">Clientes</a>
      </li>
      <li class="sidebar-nav-item">
        <a class="smooth-scroll" href="#Portfolio">Servicios Turísticos</a>
      </li>
      <li class="sidebar-nav-item">
        <a class="smooth-scroll" href="#Testimonials">Paquetes Turísticos</a>
      </li>
      <li class="sidebar-nav-item">
        <a class="smooth-scroll" href="#Contact">Ventas</a>
      </li>
    </ul>
  </nav>
  <!-- Header Starts -->
  <section id="Banner" class="content-section">
    <div class="container content-wrap text-center">
       </br></br></br></br></br>
      <h1>Reisen, tu agencia de viajes</h1>
      <h3>
          <em>¡Bienvenido, administrador/a!</em>
        </h3>
      </br>
    </div>
    <div class="overlay"></div>
  </section>
  
  <!-- Header Ends -->
  <!-- USUARIOS -->
  <section id="About" class="content-section">
    <div class="container text-center">
      <div class="row">
        <div class="col-lg-12">
          <div class="block-heading">
            <h2>Empleados y Usuarios</h2>
          </div>
          <a href="crearUsuarios.jsp"><u>Registrar un nuevo empleado y usuario</u></a>
          
          <form action="SvConsultaEmpleados" method="GET">
            <a href="SvConsultaEmpleados" class="SUBMIT"><u>Ver empleados registrados</u></a>
          </form>
          
          <form action="SvConsultaUsuarios" method="GET">
            <a href="SvConsultaUsuarios" class="SUBMIT"><u>Ver usuarios registrados</u></a>
          </form>
          
          
         </div>
      </div>
    </div>
  </section>
  <!-- CLIENTES -->
  <section id="Services" class="content-section text-center">
    <div class="container">
      <div class="block-heading">
        <h2>Clientes</h2>
        <a href="crearClientes.jsp"><u>Registrar un nuevo cliente</u></a>
        
        <form action="SvConsultaClientes" method="GET">
            <a href="SvConsultaClientes" class="SUBMIT"><u>Ver clientes registrados</u></a>
        </form>
        
     </div>
   </div>
  </section>
  <!-- SERVICIOS -->
  <section class="content-section text-center" id="Portfolio">
    <div class="container">
      <div class="block-heading">
        <h2>Servicios Turísticos</h2>
        <p>Productos individuales.</p>
        <a href="crearServicios.jsp"><u>Registrar un nuevo servicio</u></a>
       
        <form action="SvConsultaServicios" method="GET">
            <a href="SvConsultaServicios" class="SUBMIT"><u>Ver servicios registrados</u></a>
        </form>
       </div>
        
    </div>
  </section>
  <!-- PAQUETES -->
  <section id="Testimonials" class="content-section">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <div class="block-heading">
            <h2>Paquetes Turísticos</h2>
            <p>Conjunto de dos o más servicios turísticos, con un descuento del 10% de su valor total.</p>
            <a href="crearPaquetes.jsp"><u>Registrar un nuevo paquete</u></a>
            <form action="SvConsultaPaquetes" method="GET">
            <a href="SvConsultaPaquetes" class="SUBMIT"><u>Ver paquetes registrados</u></a>
        </form>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- VENTAS -->
  <section id="Contact" class="content-section">
    <div class="container">
      <div class="block-heading">
        <h2>Ventas</h2>
        <p><b>¡Muy importante!</b> Para registrar una nueva venta <b>es requisito</b> contar con el usuario del vendedor, el cliente comprador y el servicio/paquete a comprar previamente registrados.</p>
        <a href="crearVentas.jsp"><u>Registrar una nueva venta</u></a>
        <form action="SvConsultaVentas" method="GET">
            <a href="SvConsultaVentas" class="SUBMIT"><u>Ver ventas registrados</u></a>
        </form>
      </div>
    </div>
  </section>
  
  
  <%}%>
</body>

</html>
