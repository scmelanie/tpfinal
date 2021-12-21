
package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Empleado implements Serializable {
    
    //DECLARACIÓN DE VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEmpleado;
    @Basic
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private String dniEmpleado;
    private String direccionEmpleado;
    private String nacionalidadEmpleado;
    private String celularEmpleado;
    private String emailEmpleado;
    private String cargoEmpleado;
    private double sueldoEmpleado;
    @Temporal(TemporalType.DATE)
    private Date fechaNacEmpleado;
    //Relaciones entre clases
    @OneToOne
    private Usuario usuario;
    
    
    
    //CONSTRUCTORES
    public Empleado() {
    }

    public Empleado(int idEmpleado, String nombreEmpleado, String apellidoEmpleado, String dniEmpleado, String direccionEmpleado, String nacionalidadEmpleado, String celularEmpleado, String emailEmpleado, String cargoEmpleado, double sueldoEmpleado, Date fechaNacEmpleado, Usuario usuario) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.dniEmpleado = dniEmpleado;
        this.direccionEmpleado = direccionEmpleado;
        this.nacionalidadEmpleado = nacionalidadEmpleado;
        this.celularEmpleado = celularEmpleado;
        this.emailEmpleado = emailEmpleado;
        this.cargoEmpleado = cargoEmpleado;
        this.sueldoEmpleado = sueldoEmpleado;
        this.fechaNacEmpleado = fechaNacEmpleado;
        this.usuario = usuario;
    }
    
    
    
    //GETTERS Y SETTERS
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }

    public void setDireccionEmpleado(String direccionEmpleado) {
        this.direccionEmpleado = direccionEmpleado;
    }

    public String getNacionalidadEmpleado() {
        return nacionalidadEmpleado;
    }

    public void setNacionalidadEmpleado(String nacionalidadEmpleado) {
        this.nacionalidadEmpleado = nacionalidadEmpleado;
    }

    public String getCelularEmpleado() {
        return celularEmpleado;
    }

    public void setCelularEmpleado(String celularEmpleado) {
        this.celularEmpleado = celularEmpleado;
    }

    public String getEmailEmpleado() {
        return emailEmpleado;
    }

    public void setEmailEmpleado(String emailEmpleado) {
        this.emailEmpleado = emailEmpleado;
    }

    public String getCargoEmpleado() {
        return cargoEmpleado;
    }

    public void setCargoEmpleado(String cargoEmpleado) {
        this.cargoEmpleado = cargoEmpleado;
    }

    public double getSueldoEmpleado() {
        return sueldoEmpleado;
    }

    public void setSueldoEmpleado(double sueldoEmpleado) {
        this.sueldoEmpleado = sueldoEmpleado;
    }

    public Date getFechaNacEmpleado() {
        return fechaNacEmpleado;
    }

    public void setFechaNacEmpleado(Date fechaNacEmpleado) {
        this.fechaNacEmpleado = fechaNacEmpleado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
