
package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Venta implements Serializable {
    
    //DECLARACIÓN DE VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nroVenta;
    @Basic
    private String medioPago;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenta;
    //Relaciones entre clases
    @ManyToOne
    private Paquete paquete;
    @ManyToOne
    private Servicio servicio;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Usuario usuario;
    
    
    
    //CONSTRUCTORES
    public Venta() {
    }

    public Venta(int nroVenta, String medioPago, Date fechaVenta, Paquete paquete, Servicio servicio, Cliente cliente, Usuario usuario) {
        this.nroVenta = nroVenta;
        this.medioPago = medioPago;
        this.fechaVenta = fechaVenta;
        this.paquete = paquete;
        this.servicio = servicio;
        this.cliente = cliente;
        this.usuario = usuario;
    }
    
    
    
    
    //GETTERS Y SETTERS
    public int getNroVenta() {
        return nroVenta;
    }

    public void setNroVenta(int nroVenta) {
        this.nroVenta = nroVenta;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
