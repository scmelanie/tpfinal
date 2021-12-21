
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Cliente;
import logica.Empleado;
import logica.Paquete;
import logica.Servicio;
import logica.Usuario;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {
    
    
    //INSTANCIA A LAS CLASES JPA CONTROLLER
    ClienteJpaController clienteJPA = new ClienteJpaController();
    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();
    PaqueteJpaController paqueteJPA = new PaqueteJpaController();
    ServicioJpaController servicioJPA = new ServicioJpaController();
    UsuarioJpaController usuarioJPA = new UsuarioJpaController();
    VentaJpaController ventaJPA = new VentaJpaController();

    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                              E M P L E A D O                                                 //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void crearEmpleado(Empleado unEmpleado, Usuario unUsuario) {
        
        //el Usuario se crea PRIMERO para poder asignar al empleado
        usuarioJPA.create(unUsuario);
        empleadoJPA.create(unEmpleado);
    }
    
    public List<Empleado> traerEmpleados() {
        
        return empleadoJPA.findEmpleadoEntities();
    }
    
    public Empleado buscarEmpleado(int id) {
        return empleadoJPA.findEmpleado(id);
    }
 
    public void modificarEmpleado(Empleado empleado) {
        try {
            empleadoJPA.edit(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarEmpleado(int id) {
        try {
            empleadoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarEmpleado2(int idEmpleado) {
        try {
            empleadoJPA.destroy(idEmpleado);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    


    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                              C L I E N T E                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
     public void crearCliente(Cliente unCliente) {
         
         clienteJPA.create(unCliente);
    }
    
    public List<Cliente> traerClientes() {
        return clienteJPA.findClienteEntities();
    } 
    
     public Cliente buscarCliente(int id) {
         return clienteJPA.findCliente(id);
    }
     
     public void modificarCliente(Cliente cliente) {
        try {
            clienteJPA.edit(cliente);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
    public void eliminarCliente(int id) {
        try {
            clienteJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          S E R V I C I O                                                     //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void crearServicio(Servicio unServicio) {
        
        servicioJPA.create(unServicio);
    }

    public List<Servicio> traerServicios() {
        
        return servicioJPA.findServicioEntities();
        
    }
    
    public Servicio buscarServicio(int codigo) {
        return servicioJPA.findServicio(codigo);
    }
    public void modificarServicio(Servicio servicio) {
        
        try {
            servicioJPA.edit(servicio);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarServicio(int codigo) {
        try {
            servicioJPA.destroy(codigo);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          U S U A R I O                                                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public List<Empleado> traerUsuarios() {
        
       return empleadoJPA.findEmpleadoEntities();
        
    }
    
    public List<Usuario> traerUsuariosLogin() {
        
       return usuarioJPA.findUsuarioEntities();
        
    }

    public Usuario buscarUsuario(int id) {
        return usuarioJPA.findUsuario(id);
    }

    /*public void modificarUsuario(Usuario usu) {
    try {
    usuarioJPA.edit(usu);
    } catch (Exception ex) {
    Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
    }*/

    public void eliminarUsuario(int id) {
        try {
            usuarioJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

   public void eliminarUsuario2(int idUsuario) {
        try {
            usuarioJPA.destroy(idUsuario);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   
   
   
   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          P A Q U E T E                                                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void crearPaquete(Paquete paquete) {
        paqueteJPA.create(paquete);
    }

    public Paquete buscarPaquete(int codigo) {
        return paqueteJPA.findPaquete(codigo);
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          V E N T A                                                           //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    

    public void crearVenta(Venta venta) {
        ventaJPA.create(venta);
    }

    public Servicio buscarServicio2(int servicio) {
        return servicioJPA.findServicio(servicio);
    }

    public Paquete buscarPaquete2(int paquete) {
        return paqueteJPA.findPaquete(paquete);
    }

    public Cliente buscarCliente2(int cliente) {
        return clienteJPA.findCliente(cliente);
    }

    public Usuario buscarUsuario2(int usuario) {
        return usuarioJPA.findUsuario(usuario);
    }

    public List<Paquete> traerPaquetes() {
        return paqueteJPA.findPaqueteEntities();
    }

    public List<Venta> traerVentas() {
        return ventaJPA.findVentaEntities();
    }

    public void modificarPaquete(Paquete paquete) {
        try {
            paqueteJPA.edit(paquete);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Venta buscarVenta(int codigo) {
        return ventaJPA.findVenta(codigo);
    }

    public void modificarVenta(Venta venta) {
        try {
            ventaJPA.edit(venta);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarPaquete(int id) {
        try {
            paqueteJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarServicio2(int id) {
        try {
            servicioJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarVenta(int codigo) {
        try {
            ventaJPA.destroy(codigo);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
}
