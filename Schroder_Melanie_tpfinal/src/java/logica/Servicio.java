package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Servicio implements Serializable {

    //DECLARACIÓN DE VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigoServicio;
    @Basic
    private String nombreServicio;
    private String descripcionServicio;
    private String destinoServicio;
    private double costoServicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServicio;
    //Relaciones entre clases
    @OneToMany
    private List<Venta> listaDeVentasDelServicio;
    @ManyToMany
    private List<Paquete> paquetesConElServicio;

    //CONSTRUCTORES
    public Servicio() {
    }

    public Servicio(int codigoServicio, String nombreServicio, String descripcionServicio, String destinoServicio, double costoServicio, Date fechaServicio, List<Venta> listaDeVentasDelServicio, List<Paquete> paquetesConElServicio, boolean enVigencia) {
        this.codigoServicio = codigoServicio;
        this.nombreServicio = nombreServicio;
        this.descripcionServicio = descripcionServicio;
        this.destinoServicio = destinoServicio;
        this.costoServicio = costoServicio;
        this.fechaServicio = fechaServicio;
        this.listaDeVentasDelServicio = listaDeVentasDelServicio;
        this.paquetesConElServicio = paquetesConElServicio;

    }

    //GETTERS Y SETTERS 
    public int getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public String getDestinoServicio() {
        return destinoServicio;
    }

    public void setDestinoServicio(String destinoServicio) {
        this.destinoServicio = destinoServicio;
    }

    public double getCostoServicio() {
        return costoServicio;
    }

    public void setCostoServicio(double costoServicio) {
        this.costoServicio = costoServicio;
    }

    public Date getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(Date fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public List<Venta> getListaDeVentasDelServicio() {
        return listaDeVentasDelServicio;
    }

    public void setListaDeVentasDelServicio(List<Venta> listaDeVentasDelServicio) {
        this.listaDeVentasDelServicio = listaDeVentasDelServicio;
    }

    public List<Paquete> getPaquetesConElServicio() {
        return paquetesConElServicio;
    }

    public void setPaquetesConElServicio(List<Paquete> paquetesConElServicio) {
        this.paquetesConElServicio = paquetesConElServicio;
    }

}
