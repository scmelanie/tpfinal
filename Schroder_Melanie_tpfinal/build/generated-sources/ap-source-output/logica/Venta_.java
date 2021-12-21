package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Cliente;
import logica.Paquete;
import logica.Servicio;
import logica.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-17T17:21:49")
@StaticMetamodel(Venta.class)
public class Venta_ { 

    public static volatile SingularAttribute<Venta, Cliente> cliente;
    public static volatile SingularAttribute<Venta, Servicio> servicio;
    public static volatile SingularAttribute<Venta, Usuario> usuario;
    public static volatile SingularAttribute<Venta, Integer> nroVenta;
    public static volatile SingularAttribute<Venta, String> medioPago;
    public static volatile SingularAttribute<Venta, Paquete> paquete;
    public static volatile SingularAttribute<Venta, Date> fechaVenta;

}