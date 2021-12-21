
package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import persistencia.ControladoraPersistencia;

public class ControladoraLogica {
    
    //Instancia a la controladora de Persistencia
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                              C L I E N T E                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void crearCliente(String nombre, String apellido, String dni, Date fechaNac, String direccion, String nacionalidad, String email, String telefono) {
    
        //Creo el objeto Cliente
        Cliente unCliente = new Cliente();
        
        //Asigno los valores a CLIENTE
        unCliente.setNombreCliente(nombre);
        unCliente.setApellidoCliente(apellido);
        unCliente.setDniCliente(dni);
        unCliente.setFechaNacCliente(fechaNac);
        unCliente.setDireccionCliente(direccion);
        unCliente.setNacionalidadCliente(nacionalidad);
        unCliente.setEmailCliente(email);
        unCliente.setCelularCliente(telefono);
        
        //Llamo al metodo de la controladora de persistencia y le paso el Cliente creado
        controlPersis.crearCliente(unCliente);
        
        
    }
    
    public List<Cliente> traerClientes() {
        return controlPersis.traerClientes();
    }
    
    public Cliente buscarCliente(int id) {
        return controlPersis.buscarCliente(id);
    }
    
    public void modificarCliente(Cliente cliente) {
        controlPersis.modificarCliente(cliente);
    }
    
    public void eliminarCliente(int id) {
        controlPersis.eliminarCliente(id);
    }
 

  

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                              E M P L E A D O                                                 //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //el metodo recibe parametros desde el Servlet
    public void crearEmpleado(String nombre, String apellido, String dni, Date fechaNac, String direccion, 
            String nacionalidad, String email, String telefono, String cargo, Double sueldo, 
            String usuario, String contra) {
        
        //Creo el objeto empleado y el objeto usuario
        Empleado unEmpleado = new Empleado();
        Usuario unUsuario = new Usuario();
        
        //Asigno los valores a EMPLEADO
        unEmpleado.setNombreEmpleado(nombre);
        unEmpleado.setApellidoEmpleado(apellido);
        unEmpleado.setDniEmpleado(dni);
        unEmpleado.setDireccionEmpleado(direccion);
        unEmpleado.setNacionalidadEmpleado(nacionalidad);
        unEmpleado.setFechaNacEmpleado(fechaNac);
        unEmpleado.setCelularEmpleado(telefono);
        unEmpleado.setEmailEmpleado(email);
        unEmpleado.setCargoEmpleado(cargo);
        unEmpleado.setSueldoEmpleado(sueldo);
        
        //Asigno los valores a USUARIO
        unUsuario.setNombreUsuario(usuario);
        unUsuario.setContraUsuario(contra);
        
        //Asigno un USUARIO a un EMPLEADO
        unEmpleado.setUsuario(unUsuario);
        
        //Llamo al metodo de la controladora de persistencia y le paso el Empleado y el Usuario creados
        controlPersis.crearEmpleado(unEmpleado, unUsuario);
        
    }
    
    
    public List<Empleado> traerEmpleados() {
        return controlPersis.traerEmpleados();
        
    }
    
    public Empleado buscarEmpleado(int id) {
        return controlPersis.buscarEmpleado(id);
    }
    
    public void modificarEmpleado(Empleado empleado) {
        controlPersis.modificarEmpleado(empleado);
    }
    
    public void eliminarEmpleado(int id) {
        controlPersis.eliminarEmpleado(id);
    }

    public void eliminarEmpleado2(int idEmpleado) {
        controlPersis.eliminarEmpleado2(idEmpleado);
    }

    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          U S U A R I O                                                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Empleado> traerUsuarios() {
        return controlPersis.traerUsuarios();
    }
    
    //Recibo un usuario y contra desde la interfaz
    public boolean verificarUsuario (String usuario, String contra) {
        
        //traigo una lista con usuarios y contras
        List <Usuario> listaUsuarios = controlPersis.traerUsuariosLogin();
        
        if(listaUsuarios!=null) {
            for(Usuario user : listaUsuarios) {
                if (user.getNombreUsuario().equals(usuario)&&user.getContraUsuario().equals(contra)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public Usuario buscarUsuario(int id) {
        return controlPersis.buscarUsuario(id);
    }
    
    /* public void modificarUsuario(Usuario usu) {
    controlPersis.modificarUsuario(usu);
    }*/
    
    public void eliminarUsuario(int id) {
         controlPersis.eliminarUsuario(id);
    }
    
    public void eliminarUsuario2(int idUsuario) {
        controlPersis.eliminarUsuario2(idUsuario);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          V E N T A                                                           //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void crearVenta(int servicio, int paquete, int cliente, int usuario, Date fecha, String medioPago) {
    
        //Creo el objeto Venta
        Venta venta = new Venta ();
        
        //Busco los objetos relacionados y los almaceno en variables
        
        Servicio srv= buscarServicio2(servicio);
        Paquete pqt = buscarPaquete2(paquete);
        Cliente clien = buscarCliente2(cliente);
        Usuario usu = buscarUsuario2(usuario);
        
        
        //Seteo los valores
        venta.setCliente(clien);
        venta.setServicio(srv);
        venta.setPaquete(pqt);
        venta.setUsuario(usu);
        venta.setFechaVenta(fecha);
        venta.setMedioPago(medioPago);
        
        controlPersis.crearVenta(venta);
        
    
    
    }

   
   
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          P A Q U E T E                                                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void crearPaquete(String[] servicioElegido) {
        
        Paquete paquete = new Paquete();
        
        //Inicializa una lista para guardar los servicios
        List<Servicio> serviciosPorPaquete = new ArrayList<>();
        
        //Inicializa un acumulador para el monto
        double montoTotal = 0;
        
        //Itera en el array[]
        for (String codigo : servicioElegido){
            //Convierte el codigo a int
            int cod = Integer.parseInt(codigo);
            
            //Busca el servicio y lo almaceno en un objeto Servicio
            Servicio servicio = buscarServicio(cod);
            
            //Agrega el servicio en una lista
            serviciosPorPaquete.add(servicio);
            
            //Obtiene el valor del servicio y lo suma al total
            montoTotal = montoTotal + servicio.getCostoServicio();
        }
        
        
        //Descuento
        double descuento = 10;
        double montoConDescuento = montoTotal - ((montoTotal * descuento)/100);
        
        //Setea lista de servicios con sus costos
        paquete.setListaServiciosPorPaquete(serviciosPorPaquete);
        paquete.setCostoPaquete(montoConDescuento);
        
        controlPersis.crearPaquete(paquete);
        
    }
    
    public Paquete buscarPaquete(int codigo) {
        
        return controlPersis.buscarPaquete(codigo);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          S E R V I C I O                                                     //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void crearServicio(String nombre, String descripcion, String destino, Date fecha, double costo) {
        
        //Creo el objeto Servicio
        Servicio unServicio = new Servicio();
        
        //Asigno los valores a SERVICIO
        unServicio.setNombreServicio(nombre);
        unServicio.setDescripcionServicio(descripcion);
        unServicio.setDestinoServicio(destino);
        unServicio.setFechaServicio(fecha);
        unServicio.setCostoServicio(costo);
        
        
        //Llamo al metodo de la controladora de persistencia y le paso el Servicio creado
        controlPersis.crearServicio(unServicio);
    }

    public List<Servicio> traerServicios() {
        
        return controlPersis.traerServicios();
    }

    public Servicio buscarServicio(int codigo) {
        
        return controlPersis.buscarServicio(codigo);
    }

    public void modificarServicio(Servicio servicio) {
        
        controlPersis.modificarServicio(servicio);
    }

    public void eliminarServicio(int codigo) {
        controlPersis.eliminarServicio(codigo);
    }

    private Servicio buscarServicio2(int servicio) {
        return controlPersis.buscarServicio2(servicio);
    }

    private Paquete buscarPaquete2(int paquete) {
        return controlPersis.buscarPaquete2(paquete);
    }

    private Cliente buscarCliente2(int cliente) {
        return controlPersis.buscarCliente2(cliente);
    }

    private Usuario buscarUsuario2(int usuario) {
        return controlPersis.buscarUsuario2(usuario);
    }

    public List<Paquete> traerPaquetes() {
        return controlPersis.traerPaquetes();
    }

    public List<Venta> traerVentas() {
        return controlPersis.traerVentas();
    }

    public void modificarPaquete(String[] paqueteModificado) {
        
        Paquete paquete = new Paquete();
        
        //Inicializa una lista para guardar los servicios
        List<Servicio> serviciosPorPaquete = new ArrayList<>();
        
        //Inicializa un acumulador para el monto
        double montoTotal = 0;
        
        //Itera en el array[]
        for (String codigo : paqueteModificado){
            //Convierte el codigo a int
            int servicio = Integer.parseInt(codigo);
            
            //Busca el servicio y lo almaceno en un objeto Servicio
            Servicio srv = buscarServicio2(servicio);
            
            //Agrega el servicio en una lista
            serviciosPorPaquete.add(srv);
            
            //Obtiene el valor del servicio y lo suma al total
            montoTotal = montoTotal + srv.getCostoServicio();
        }
        
        
        //Descuento
        double descuento = 10;
        double montoConDescuento = montoTotal - ((montoTotal * descuento)/100);
        
        //Setea lista de servicios con sus costos
        paquete.setListaServiciosPorPaquete(serviciosPorPaquete);
        paquete.setCostoPaquete(montoConDescuento);
        
        controlPersis.modificarPaquete(paquete);
        
    }

    public Venta buscarVenta(int codigo) {
        return controlPersis.buscarVenta(codigo);
    }

    public void modificarVenta(Venta venta) {
        controlPersis.modificarVenta(venta);
    }

    public void eliminarPaquete(int id) {
        controlPersis.eliminarPaquete(id);
    }

    public void eliminarServicio2(int id) {
        controlPersis.eliminarServicio2(id);
    }

    public void eliminarVenta(int codigo) {
        controlPersis.eliminarVenta(codigo);
    }
    
    
}

    
