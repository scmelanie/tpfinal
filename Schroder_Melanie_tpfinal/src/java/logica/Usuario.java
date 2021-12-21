package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Usuario implements Serializable {

    //DECLARACIÓN DE VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUsuario;
    @Basic
    private String nombreUsuario;
    private String contraUsuario;
    //Relaciones entre clases
    @OneToOne
    private Empleado empleado;
    @OneToMany
    private List<Venta> listadeVentas;

    //CONSTRUCTORES
    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, String contraUsuario, Empleado empleado, List<Venta> listadeVentas) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contraUsuario = contraUsuario;
        this.empleado = empleado;
        this.listadeVentas = listadeVentas;
    }

    //GETTERS Y SETTERS
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraUsuario() {
        return contraUsuario;
    }

    public void setContraUsuario(String contraUsuario) {
        this.contraUsuario = contraUsuario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Venta> getListadeVentas() {
        return listadeVentas;
    }

    public void setListadeVentas(List<Venta> listadeVentas) {
        this.listadeVentas = listadeVentas;
    }

}
