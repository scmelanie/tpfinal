package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Cliente implements Serializable {

    //DECLARACIÓN DE VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCliente;
    @Basic
    private String nombreCliente;
    private String apellidoCliente;
    private String dniCliente;
    private String direccionCliente;
    private String nacionalidadCliente;
    private String celularCliente;
    private String emailCliente;
    @Temporal(TemporalType.DATE)
    private Date fechaNacCliente;
    //Relaciones entre clases
    @OneToMany
    private List<Venta> listaDeCompras;

    //CONSTRUCTORES
    public Cliente() {
    }

    public Cliente(int idCliente, String nombreCliente, String apellidoCliente, String dniCliente, String direccionCliente, String nacionalidadCliente, String celularCliente, String emailCliente, Date fechaNacCliente, List<Venta> listaDeCompras) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.dniCliente = dniCliente;
        this.direccionCliente = direccionCliente;
        this.nacionalidadCliente = nacionalidadCliente;
        this.celularCliente = celularCliente;
        this.emailCliente = emailCliente;
        this.fechaNacCliente = fechaNacCliente;
        this.listaDeCompras = listaDeCompras;
    }

    //GETTERS Y SETTERS
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getNacionalidadCliente() {
        return nacionalidadCliente;
    }

    public void setNacionalidadCliente(String nacionalidadCliente) {
        this.nacionalidadCliente = nacionalidadCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public Date getFechaNacCliente() {
        return fechaNacCliente;
    }

    public void setFechaNacCliente(Date fechaNacCliente) {
        this.fechaNacCliente = fechaNacCliente;
    }

    public List<Venta> getListaDeCompras() {
        return listaDeCompras;
    }

    public void setListaDeCompras(List<Venta> listaDeCompras) {
        this.listaDeCompras = listaDeCompras;
    }

}
