package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-17T17:21:49")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, String> direccionEmpleado;
    public static volatile SingularAttribute<Empleado, String> dniEmpleado;
    public static volatile SingularAttribute<Empleado, String> emailEmpleado;
    public static volatile SingularAttribute<Empleado, String> cargoEmpleado;
    public static volatile SingularAttribute<Empleado, Integer> idEmpleado;
    public static volatile SingularAttribute<Empleado, Double> sueldoEmpleado;
    public static volatile SingularAttribute<Empleado, String> apellidoEmpleado;
    public static volatile SingularAttribute<Empleado, Usuario> usuario;
    public static volatile SingularAttribute<Empleado, String> nombreEmpleado;
    public static volatile SingularAttribute<Empleado, String> nacionalidadEmpleado;
    public static volatile SingularAttribute<Empleado, String> celularEmpleado;
    public static volatile SingularAttribute<Empleado, Date> fechaNacEmpleado;

}