
package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Paquete implements Serializable {
    
    //DECLARACIÓN DE VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigoPaquete;
    @Basic
    private double costoPaquete;
    //Relaciones entre clases
    @OneToMany
    private List<Venta> listaDeVentasDelPaquete;
    @ManyToMany
    private List<Servicio> listaServiciosPorPaquete;

    
    
    
    //CONSTRUCTORES
    public Paquete() {
    }

    public Paquete(int codigoPaquete, double costoPaquete, List<Venta> listaDeVentasDelPaquete, List<Servicio> listaServiciosPorPaquete) {
        this.codigoPaquete = codigoPaquete;
        this.costoPaquete = costoPaquete;
        this.listaDeVentasDelPaquete = listaDeVentasDelPaquete;
        this.listaServiciosPorPaquete = listaServiciosPorPaquete;
    }

    
    
    
    
    
    //GETTERS Y SETTERS
    public int getCodigoPaquete() {
        return codigoPaquete;
    }

    public void setCodigoPaquete(int codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public double getCostoPaquete() {
        return costoPaquete;
    }

    public void setCostoPaquete(double costoPaquete) {
        this.costoPaquete = costoPaquete;
    }

    public List<Venta> getListaDeVentasDePaquetes() {
        return listaDeVentasDelPaquete;
    }

    public void setListaDeVentasDePaquetes(List<Venta> listaDeVentasDePaquetes) {
        this.listaDeVentasDelPaquete = listaDeVentasDePaquetes;
    }

    public List<Servicio> getListaServiciosPorPaquete() {
        return listaServiciosPorPaquete;
    }

    public void setListaServiciosPorPaquete(List<Servicio> listaServiciosPorPaquete) {
        this.listaServiciosPorPaquete = listaServiciosPorPaquete;
    }
  
}
